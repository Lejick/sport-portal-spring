package org.portal.back.pinnacle.grabber;

import org.portal.back.pinnacle.Constants;
import org.portal.back.pinnacle.api.Parameter;
import org.portal.back.pinnacle.api.PinnacleAPI;
import org.portal.back.pinnacle.api.PinnacleException;
import org.portal.back.pinnacle.api.dataobjects.Fixtures;
import org.portal.back.pinnacle.api.dataobjects.Line;
import org.portal.back.pinnacle.api.dataobjects.Odds;
import org.portal.back.pinnacle.api.enums.*;
import org.portal.back.util.DualLogger;


public class PinnacleConnector {
    private static final DualLogger LOGGER = new DualLogger(PinnacleConnector.class);

    private String username = Constants.USER;
    private String password = Constants.PASS;

    public Fixtures getFixtures(int sportId) {
        try {
            PinnacleAPI api = PinnacleAPI.open(username, password);
            Parameter parameter = Parameter.newInstance();
            parameter.sportId(sportId);
            parameter.isLive(false);

            Fixtures fixtures = api.getFixturesAsObject(parameter);
            return fixtures;
        } catch (PinnacleException ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    public Odds getOdds(int sportId) {
        try {
            PinnacleAPI api = PinnacleAPI.open(username, password);
            Parameter parameter = Parameter.newInstance();
            parameter.sportId(sportId);
            parameter.oddsFormat(ODDS_FORMAT.DECIMAL);
            Odds odds = api.getOddsAsObject(parameter);
            return odds;
        } catch (PinnacleException ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    public Line getLine(long eventId, TEAM_TYPE teamType, PERIOD period, BET_TYPE betType,int sportId, long leagueId ) {
        try {
            PinnacleAPI api = PinnacleAPI.open(username, password);
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
            LOGGER.error(ex);
        }

        return null;

    }

}
