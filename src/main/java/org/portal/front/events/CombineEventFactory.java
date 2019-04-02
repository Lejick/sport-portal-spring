package org.portal.front.events;

import org.portal.back.model.LineEvent;
import org.portal.back.pinnacle.api.enums.BET_TYPE;
import org.portal.back.pinnacle.api.enums.SIDE_TYPE;
import org.portal.back.pinnacle.api.enums.TEAM_TYPE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombineEventFactory {
    public static List<CombineMoneyLineOdds> createMoneyLineOddsList(List<LineEvent> lineEventList) {
        List<CombineMoneyLineOdds> combineMoneyLineOdds = new ArrayList<>();
        List<LineEvent> home = getAllMoneyLine(lineEventList, TEAM_TYPE.TEAM_1);
        List<LineEvent> away = getAllMoneyLine(lineEventList, TEAM_TYPE.TEAM_2);
        for (LineEvent homeEvent : home) {
            for (LineEvent awayEvent : away) {
                if (homeEvent.getCheckDate().equals(awayEvent.getCheckDate())) {
                    combineMoneyLineOdds.add(new CombineMoneyLineOdds(homeEvent, awayEvent, homeEvent.getCheckDate()));
                    break;
                }
            }
        }
        Collections.sort(combineMoneyLineOdds, (o1, o2) -> {
            if (o1.getDate().before(o2.getDate())) {
                return 1;
            }
            return -1;
        });
        return combineMoneyLineOdds;
    }

    public static List<CombineTotalsOdds> createTotalsOddsList(List<LineEvent> lineEventList) {
        List<CombineTotalsOdds> combineOdds = new ArrayList<>();
        List<LineEvent> under = getAllTotalLine(lineEventList, SIDE_TYPE.UNDER);
        List<LineEvent> over = getAllTotalLine(lineEventList, SIDE_TYPE.OVER);
        for (LineEvent underEvent : under) {
            for (LineEvent overEvent : over) {
                if (overEvent.getCheckDate().equals(underEvent.getCheckDate()) && overEvent.getTotal().equals(underEvent.getTotal())) {
                    combineOdds.add(new CombineTotalsOdds(underEvent, overEvent, overEvent.getCheckDate(), underEvent.getTotal()));
                    break;
                }
            }
        }
        Collections.sort(combineOdds, (o1, o2) -> {
            if (o1.getTotal().compareTo(o2.getTotal()) > 0) {
                return 1;
            }
            if (o1.getTotal().compareTo(o2.getTotal()) < 0) {
                return -1;
            }
            if (o1.getDate().before(o2.getDate())) {
                return 1;
            }

            return -1;
        });
        return combineOdds;
    }

    public static List<CombineSpreadOdds> createSpreadOddsList(List<LineEvent> lineEventList) {
        List<CombineSpreadOdds> combineOdds = new ArrayList<>();
        List<LineEvent> home = getAllSpread(lineEventList, TEAM_TYPE.TEAM_1);
        List<LineEvent> away = getAllSpread(lineEventList, TEAM_TYPE.TEAM_2);
        for (LineEvent homeEvent : home) {
            for (LineEvent awayEvent : away) {
                if (homeEvent.getCheckDate().equals(awayEvent.getCheckDate()) && homeEvent.getSpread().equals(awayEvent.getSpread())) {
                    combineOdds.add(new CombineSpreadOdds(homeEvent, awayEvent, homeEvent.getCheckDate(), awayEvent.getSpread()));
                    break;
                }
            }
        }
        Collections.sort(combineOdds, (o1, o2) -> {
            if (o1.getSpread().compareTo(o2.getSpread()) > 0) {
                return 1;
            }
            if (o1.getSpread().compareTo(o2.getSpread()) < 0) {
                return -1;
            }
            if (o1.getDate().before(o2.getDate())) {
                return 1;
            }

            return -1;
        });
        return combineOdds;
    }

    private static List<LineEvent> getAllMoneyLine(List<LineEvent> lineEventList, TEAM_TYPE teamType) {
        List<LineEvent> homeEvents = new ArrayList<>();
        for (LineEvent event : lineEventList) {
            if (event.getBet_type().equals(BET_TYPE.MONEYLINE.toAPI()) &&
                    event.getTeam_type() != null &&
                    event.getTeam_type().equals(teamType.toAPI())) {
                homeEvents.add(event);
            }
        }
        return homeEvents;
    }

    private static List<LineEvent> getAllSpread(List<LineEvent> lineEventList, TEAM_TYPE teamType) {
        List<LineEvent> homeEvents = new ArrayList<>();
        for (LineEvent event : lineEventList) {
            if (event.getBet_type().equals(BET_TYPE.SPREAD.toAPI()) &&
                    event.getTeam_type() != null &&
                    event.getTeam_type().equals(teamType.toAPI())) {
                homeEvents.add(event);
            }
        }
        return homeEvents;
    }

    private static List<LineEvent> getAllTotalLine(List<LineEvent> lineEventList, SIDE_TYPE sideType) {
        List<LineEvent> homeEvents = new ArrayList<>();
        for (LineEvent event : lineEventList) {
            if (event.getBet_type().equals(BET_TYPE.TOTAL_POINTS.toAPI()) && event.getSide_type().equals(sideType.toAPI())) {
                homeEvents.add(event);
            }
        }
        return homeEvents;
    }

}
