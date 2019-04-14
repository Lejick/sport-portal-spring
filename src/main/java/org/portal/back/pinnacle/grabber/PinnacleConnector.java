package org.portal.back.pinnacle.grabber;

import org.portal.back.pinnacle.api.Parameter;
import org.portal.back.pinnacle.api.PinnacleAPI;
import org.portal.back.pinnacle.api.PinnacleException;
import org.portal.back.pinnacle.api.dataobjects.Fixtures;
import org.portal.back.pinnacle.api.dataobjects.Line;
import org.portal.back.pinnacle.api.dataobjects.Odds;
import org.portal.back.pinnacle.api.enums.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PinnacleConnector {
    private final Logger LOGGER = LoggerFactory.getLogger(PinnacleConnector.class);
    private final String pass;


    public PinnacleConnector(String pass) {
        this.pass = pass;
    }

    public Fixtures getFixtures(int sportId) {
        try {
            PinnacleAPI api = PinnacleAPI.open(pass);
            Parameter parameter = Parameter.newInstance();
            parameter.sportId(sportId);
            Fixtures fixtures = api.getFixturesAsObject(parameter);
            return fixtures;
        } catch (PinnacleException ex) {
            LOGGER.error(ex.getMessage(),ex);
        }
        return null;
    }

    public Fixtures getFixturesLive(int sportId) {
        try {
            PinnacleAPI api = PinnacleAPI.open(pass);
            Parameter parameter = Parameter.newInstance();
            parameter.sportId(sportId);
            parameter.isLive(true);
            Fixtures fixtures = api.getFixturesAsObject(parameter);
            return fixtures;
        } catch (PinnacleException ex) {
            LOGGER.error(ex.getMessage(),ex);
        }
        return null;
    }

    public Odds getOddsLive(int sportId) {
        try {
            PinnacleAPI api = PinnacleAPI.open(pass);
            Parameter parameter = Parameter.newInstance();
            parameter.sportId(sportId);
            parameter.isLive(true);
            parameter.oddsFormat(ODDS_FORMAT.DECIMAL);
            Odds odds = api.getOddsAsObject(parameter);
            return odds;
        } catch (PinnacleException ex) {
            LOGGER.error(ex.getMessage(),ex);
        }
        return null;
    }

    public Odds getOdds(int sportId) {
        try {
            PinnacleAPI api = PinnacleAPI.open(pass);
            Parameter parameter = Parameter.newInstance();
            parameter.sportId(sportId);
            parameter.oddsFormat(ODDS_FORMAT.DECIMAL);
            Odds odds = api.getOddsAsObject(parameter);
            return odds;
        } catch (PinnacleException ex) {
            LOGGER.error(ex.getMessage(),ex);
        }
        return null;
    }

    public Line getLine(long eventId, TEAM_TYPE teamType, PERIOD period, BET_TYPE betType,int sportId, long leagueId ) {
        try {
            PinnacleAPI api = PinnacleAPI.open(pass);
            ODDS_FORMAT oddsFormat = ODDS_FORMAT.DECIMAL;
            Parameter parameter = Parameter.newInstance();
            parameter.eventId(eventId);
            parameter.sportId(sportId);
            parameter.leagueId(leagueId);
            parameter.periodNumber(period);
            parameter.oddsFormat(oddsFormat);
            parameter.betType(betType);
            parameter.team(teamType);
            Line line = api.getLineAsObject(parameter);
            if (line.status() == GETLINE_RESPONSE_STATUS.SUCCESS) {
                return line;
            }
        } catch (PinnacleException ex) {
            LOGGER.error(ex.getMessage(),ex);
        }

        return null;

    }

}
