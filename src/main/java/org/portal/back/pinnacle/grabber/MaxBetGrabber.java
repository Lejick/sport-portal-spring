package org.portal.back.pinnacle.grabber;

import org.portal.back.model.*;
import org.portal.back.pinnacle.api.dataobjects.Line;
import org.portal.back.pinnacle.api.dataobjects.Odds;
import org.portal.back.pinnacle.api.enums.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public abstract class MaxBetGrabber extends AbstractGrabber {

    public MaxBetGrabber(int sportId) {
        super(sportId);
    }

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LineEventRepository lineEventRepository;

    @Autowired
    PinaccRepository pinaccRepository;

    @Override
    public void grab() {
        String credentials = pinaccRepository.findById(1L).get().getPassed();

        getLogger().info("Start to get odds for sportId=" + sportId);
        PinnacleConnector oddsConnector = new PinnacleConnector(credentials);
        long leagueId=0L;
        long eventId=0L;
        Odds odds = oddsConnector.getOdds(sportId);
        for(Odds.League league:odds.leagues()){
            leagueId=league.id();
            for(Odds.Event event:league.events()){
                eventId=event.id();
                getLogger().info("Update Max Bet  Event id: " + eventId);
                updateEvent(eventId, leagueId);
            }
        }

    }

    protected abstract Logger getLogger();

    public void updateEvent(long eventId, long leagueId) {
        Optional<Event> emh = eventRepository.findById(eventId);
        if (!emh.isPresent()) {
            return;
        }

        LineEvent fixLineTeam1 = getTennisMoneyLineEvent(eventId, TEAM_TYPE.TEAM_1, now, leagueId);
        LineEvent fixLineTeam2 = getTennisMoneyLineEvent(eventId, TEAM_TYPE.TEAM_2, now, leagueId);
        now = getCurrentTime();
        if (fixLineTeam1 != null && fixLineTeam2 != null) {
            updateDBLineEvent(emh.get(), fixLineTeam1, now, TEAM_TYPE.TEAM_1);
            updateDBLineEvent(emh.get(), fixLineTeam2, now, TEAM_TYPE.TEAM_2);
        }

    }


    private void updateDBLineEvent(Event emh, LineEvent fixLine, Date oddsTime, TEAM_TYPE teamType) {
        LineEvent dbEvent = emh.getLastMoneyLine(teamType.toAPI());
        LineEvent line = LineEntityFactory.createMoneyLine(fixLine.getPrice(), fixLine.getMax_bet(), teamType, oddsTime, emh);
        if (dbEvent == null) {
            lineEventRepository.save(line);
        } else {
            if (dbEvent.getMax_bet() == null || dbEvent.getMax_bet().compareTo(line.getMax_bet()) != 0) {
                dbEvent.setMax_bet(fixLine.getMax_bet());
                dbEvent.setPrice(fixLine.getPrice());
                lineEventRepository.save(dbEvent);
            }
        }
    }

    private LineEvent getTennisMoneyLineEvent(long eventId, TEAM_TYPE teamType, Date date, long leagueId) {
        String credentials = pinaccRepository.findById(1L).get().getPassed();
        PinnacleConnector tennisMLConnector = new PinnacleConnector(credentials);
        Line line = tennisMLConnector.getLine(eventId, teamType, PERIOD.TENNIS_MATCH, BET_TYPE.MONEYLINE, sportId, leagueId);
        if (line != null && line.status() == GETLINE_RESPONSE_STATUS.SUCCESS) {
            return createLineEvent(line, PERIOD.TENNIS_MATCH, ODDS_FORMAT.DECIMAL, BET_TYPE.MONEYLINE, teamType, date);
        }
        return null;
    }

    private LineEvent createLineEvent(Line line, PERIOD period, ODDS_FORMAT oddsFormat, BET_TYPE betType, TEAM_TYPE teamType, Date date) {
        LineEvent lineEvent = new LineEvent();
        lineEvent.setBet_type(betType.toAPI());
        lineEvent.setOdds_format(oddsFormat.toAPI());
        lineEvent.setPeriod_number(Integer.valueOf(period.toAPI()));
        lineEvent.setTeam_type(teamType.toAPI());
        lineEvent.setPrice(line.price().get());
        lineEvent.setMax_bet(line.maxRiskStake().get());
        lineEvent.setCheckDate(date);
        return lineEvent;
    }
}

