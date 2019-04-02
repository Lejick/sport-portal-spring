package org.portal.back.pinnacle.grabber;

import org.portal.back.model.Event;
import org.portal.back.model.LineEvent;
import org.portal.back.pinnacle.api.enums.*;

import java.math.BigDecimal;
import java.util.Date;

public class LineEntityFactory {
    public static LineEvent createMoneyLine(BigDecimal price, BigDecimal maxBet, TEAM_TYPE teamType, Date date, Event emh) {
        PERIOD period = PERIOD.TENNIS_MATCH;
        ODDS_FORMAT oddsFormat = ODDS_FORMAT.DECIMAL;
        BET_TYPE betType = BET_TYPE.MONEYLINE;
        LineEvent lineEvent = new LineEvent();
        lineEvent.setBet_type(betType.toAPI());
        lineEvent.setOdds_format(oddsFormat.toAPI());
        lineEvent.setPeriod_number(Integer.valueOf(period.toAPI()));
        lineEvent.setTeam_type(teamType.toAPI());
        lineEvent.setPrice(price);
        lineEvent.setCheckDate(date);
        lineEvent.setMax_bet(maxBet);
        lineEvent.setEvent(emh);
        return lineEvent;
    }

    public static LineEvent createMoneyLine(BigDecimal price, TEAM_TYPE teamType, Date date, Event emh) {
        return createMoneyLine(price, null, teamType, date, emh);
    }

    public static LineEvent createTotal(BigDecimal price, Date date, BigDecimal total, SIDE_TYPE side_type, Event emh) {
        PERIOD period = PERIOD.TENNIS_MATCH;
        ODDS_FORMAT oddsFormat = ODDS_FORMAT.DECIMAL;
        BET_TYPE betType = BET_TYPE.TOTAL_POINTS;
        LineEvent lineEvent = new LineEvent();
        lineEvent.setBet_type(betType.toAPI());
        lineEvent.setOdds_format(oddsFormat.toAPI());
        lineEvent.setPeriod_number(Integer.valueOf(period.toAPI()));
        lineEvent.setPrice(price);
        lineEvent.setCheckDate(date);
        lineEvent.setSide_type(side_type.toAPI());
        lineEvent.setTotal(total);
        lineEvent.setEvent(emh);
        return lineEvent;
    }

    public static LineEvent createSpread(BigDecimal price, Date date, BigDecimal spread, TEAM_TYPE teamType, Event emh) {
        PERIOD period = PERIOD.TENNIS_MATCH;
        ODDS_FORMAT oddsFormat = ODDS_FORMAT.DECIMAL;
        BET_TYPE betType = BET_TYPE.SPREAD;
        LineEvent lineEvent = new LineEvent();
        lineEvent.setBet_type(betType.toAPI());
        lineEvent.setOdds_format(oddsFormat.toAPI());
        lineEvent.setPeriod_number(Integer.valueOf(period.toAPI()));
        lineEvent.setPrice(price);
        lineEvent.setCheckDate(date);
        lineEvent.setTeam_type(teamType.toAPI());
        lineEvent.setSpread(spread);
        lineEvent.setEvent(emh);
        return lineEvent;
    }

}
