package org.portal.back.pinnacle.grabber;

import org.portal.back.model.Event;
import org.portal.back.model.LineEvent;
import org.portal.back.pinnacle.api.dataobjects.Fixtures;
import org.portal.back.pinnacle.api.dataobjects.Line;
import org.portal.back.pinnacle.api.enums.*;
import org.portal.back.util.DualLogger;

import java.util.Date;

public class MaxBetGrabber extends AbstractGrabber {
    private static Fixtures fixtures;
    private DualLogger LOGGER = new DualLogger(MaxBetGrabber.class);

    public MaxBetGrabber(int sportId) {
        super(sportId);
    }

    public void grab() {
        if (fixtures != null) {
            LOGGER.info("Initiate Max Bet Grabber");
            now = getCurrentTime();
            for (int i = 0; i < fixtures.league().size(); ) {
                Fixtures.League fixLeague = fixtures.league().get(i);
                if (fixLeague != null) {
                    for (int j = 0; j < fixLeague.events().size(); ) {
                        Fixtures.Event fixEvent = fixLeague.events().get(j);
                        if (fixEvent != null && Date.from(fixEvent.starts()).after(now)) {
                                LOGGER.info("Update Max Bet  L: " + fixLeague.name() + " E: " + fixEvent.home() + "/" + fixEvent.away());
                                updateEvent(fixEvent.id(), fixLeague.id());
                                j++;
                            }
                        }
                    }
                    i++;
                }
            }
            LOGGER.info("Release Max Bet Grabber");
        }

    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public void updateEvent(long eventId, long leagueId) {
        Event emh = em.find(Event.class, eventId);
        if (emh == null) {
            return;
        }

        LineEvent fixLineTeam1 = getTennisMoneyLineEvent(eventId, TEAM_TYPE.TEAM_1, now, leagueId);
        LineEvent fixLineTeam2 = getTennisMoneyLineEvent(eventId, TEAM_TYPE.TEAM_2, now, leagueId);
        now = getCurrentTime();
        if (fixLineTeam1 != null && fixLineTeam2 != null) {
            updateDBLineEvent(emh, fixLineTeam1, now, TEAM_TYPE.TEAM_1);
            updateDBLineEvent(emh, fixLineTeam2, now, TEAM_TYPE.TEAM_2);
        }

    }


    private void updateDBLineEvent(Event emh, LineEvent fixLine, Date oddsTime, TEAM_TYPE teamType) {
        LineEvent dbEvent = emh.getLastMoneyLine(teamType.toAPI());
        LineEvent line = LineEntityFactory.createMoneyLine(fixLine.getPrice(), fixLine.getMax_bet(), teamType, oddsTime, emh);
        if (dbEvent == null) {
            em.merge(line);
        } else {
            if (dbEvent.getMax_bet() == null || dbEvent.getMax_bet().compareTo(line.getMax_bet()) != 0) {
                dbEvent.setMax_bet(fixLine.getMax_bet());
                dbEvent.setPrice(fixLine.getPrice());
                em.merge(dbEvent);
            }
        }
    }

    private LineEvent getTennisMoneyLineEvent(long eventId, TEAM_TYPE teamType, Date date, long leagueId) {
        PinnacleConnector tennisMLConnector = new PinnacleConnector();
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

