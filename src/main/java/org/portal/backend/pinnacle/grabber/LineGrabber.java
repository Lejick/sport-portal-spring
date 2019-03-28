package org.portal.backend.pinnacle.grabber;

import org.portal.backend.data.model.Event;
import org.portal.backend.data.model.EventRepository;
import org.portal.backend.data.model.LineEvent;
import org.portal.backend.pinnacle.api.dataobjects.Fixtures;
import org.portal.backend.pinnacle.api.dataobjects.Odds;
import org.portal.backend.pinnacle.api.enums.*;
import org.portal.backend.util.DualLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

public class LineGrabber extends AbstractGrabber {
    @Autowired
    EventRepository eventRepository;
    public LineGrabber(int sportId) {
        super(sportId);
    }
    private  DualLogger LOGGER = new DualLogger(LineGrabber.class);

    public void grab() {
        PinnacleConnector fixtureConnector = new PinnacleConnector();
        PinnacleConnector oddsConnector = new PinnacleConnector();
        LOGGER.info("Start to get fixtures for sportId="+sportId);
        Fixtures fixtures = fixtureConnector.getFixtures(sportId);
        Odds odds = oddsConnector.getOdds(sportId);
        LOGGER.info("Start to get odds for sportId="+sportId);
        now = getCurrentTime();
        for (Odds.League league : odds.leagues()) {
            Fixtures.League fixLeague = findLeagueById(fixtures, league.id());
            if (fixLeague != null) {

                for (Odds.Event event : league.events()) {
                    Fixtures.Event fixEvent = findEventById(fixtures, event.id());
                    if (fixEvent != null) {
                        updateEvent(event.id(), fixEvent.home(), fixEvent.away(), Date.from(fixEvent.starts()),
                                league.id(), fixLeague.name(), event);

                    }
                }

            }
        }
        LOGGER.info("Line Grabber loop finish ");
    }

    private Fixtures.League findLeagueById(Fixtures fix, long id) {
        if (fix != null) {
            for (Fixtures.League league : fix.league()) {
                if (league.id().equals(id)) {
                    return league;
                }
            }
        }
        return null;
    }

    private Fixtures.Event findEventById(Fixtures fix, long id) {
        for (Fixtures.League league : fix.league()) {
            for (Fixtures.Event event : league.events()) {
                if (event.id().equals(id)) {
                    return event;
                }
            }
        }
        return null;
    }

    @Transactional
    private void merge(Event emh){
        em.merge(emh);

    }

    @Transactional
    public void updateEvent(long eventId, String home, String away, Date eventStart,
                            long leagueId, String leagueName, Odds.Event oddsEvent) {
        Event emh = em.find(Event.class, eventId);
        if (emh == null) {
            emh = new Event();
            emh.setId(eventId);
            emh.setHome(home);
            emh.setAway(away);
            emh.setSport_id(sportId);
            emh.setLeague_id(leagueId);
            emh.setLeague_name(leagueName);
            emh.setStarts(eventStart);
            eventRepository.save(emh);
        }
        if (oddsEvent.periods().get(0).moneyline().isPresent()) {
            insertMoneyLineInBase(oddsEvent, emh, leagueId);
        }
        if (oddsEvent.periods().get(0).spreads().size() > 0) {
            createSpread(oddsEvent, emh, leagueId, eventId);
        }
        if (oddsEvent.periods().get(0).totals().size() > 0) {
            createTotal(oddsEvent, emh);
        }
    }

    private void createTotal(Odds.Event oddsEvent, Event emh) {
        for (Odds.TotalPoints totalPoints : oddsEvent.periods().get(0).totals()) {
            LineEvent totalOver = LineEntityFactory.createTotal(totalPoints.over(), now, totalPoints.points(), SIDE_TYPE.OVER, emh);
            LineEvent totalUnder = LineEntityFactory.createTotal(totalPoints.under(), now, totalPoints.points(), SIDE_TYPE.UNDER, emh);
            LineEvent totalOverDB = emh.getLastTotal(totalPoints.points(), SIDE_TYPE.OVER.toAPI());
            LineEvent totalUnderDB = emh.getLastTotal(totalPoints.points(), SIDE_TYPE.UNDER.toAPI());
            if (totalOverDB == null || totalOver.getPrice().compareTo(totalOverDB.getPrice()) != 0) {
                em.merge(totalOver);
            } else {
                totalOverDB.setCheckDate(now);
                em.merge(totalOverDB);
            }
            if (totalUnderDB == null || totalUnder.getPrice().compareTo(totalUnderDB.getPrice()) != 0) {
                em.merge(totalUnder);
            } else {
                totalUnderDB.setCheckDate(now);
                em.merge(totalUnderDB);
            }
        }
    }

    private void createSpread(Odds.Event oddsEvent, Event emh, long leagueId, long eventId) {
    }

    private void insertMoneyLineInBase(Odds.Event oddsEvent, Event emh, long leagueId) {
        Odds.MoneyLine moneyLine = oddsEvent.periods().get(0).moneyline().get();
        updateDBLineEvent(emh, moneyLine, now, TEAM_TYPE.TEAM_1);
        updateDBLineEvent(emh, moneyLine, now, TEAM_TYPE.TEAM_2);

    }

    private void updateDBLineEvent(Event emh, Odds.MoneyLine moneyLine, java.util.Date oddsTime, TEAM_TYPE teamType) {
        BigDecimal oddsPrice = moneyLine.home();
        if (teamType == TEAM_TYPE.TEAM_2) {
            oddsPrice = moneyLine.away();
        }
        LineEvent dbEvent = emh.getLastMoneyLine(teamType.toAPI());
        LineEvent line = LineEntityFactory.createMoneyLine(oddsPrice, teamType, oddsTime, emh);
        if (dbEvent == null) {
            em.merge(line);
        } else {
            if (dbEvent.getPrice().compareTo(line.getPrice()) == 0) {
                dbEvent.setCheckDate(oddsTime);
                em.merge(dbEvent);
            } else {
                line.setPrice(line.getPrice());
                em.merge(line);
            }
        }
    }
}

