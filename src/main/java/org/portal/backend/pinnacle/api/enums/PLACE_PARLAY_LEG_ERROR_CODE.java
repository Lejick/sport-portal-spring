package org.portal.backend.pinnacle.api.enums;

public enum PLACE_PARLAY_LEG_ERROR_CODE {

	CANNOT_PARLAY_LIVE_GAME,
	CORRELATED,
	EVENT_NO_LONGER_AVAILABLE_FOR_BETTING,
	EVENT_NOT_OFFERED_FOR_PARLAY,
	INVALID_EVENT,
	INVALID_LEG_BET_TYPE,
	INVALID_PARLAY_BET,
	LINE_CHANGED,
	LINE_DOES_NOT_BELONG_TO_EVENT,
	LISTED_PITCHERS_SELECTION_ERROR,
	ODDS_NO_LONGER_OFFERED_FOR_PARLAY_1,
	ODDS_NO_LONGER_OFFERED_FOR_PARLAY_2,
	ODDS_NO_LONGER_OFFERED_FOR_PARLAY_3,
	OFFLINE_EVENT,
	PAST_CUTOFFTIME,
	SYSTEM_ERROR_1,
	SYSTEM_ERROR_2,
	SYSTEM_ERROR_3,
	UNDEFINED;

	public String toAPI () {
		return this.name();
	}
	
	public static PLACE_PARLAY_LEG_ERROR_CODE fromAPI (String text) {
		try {
			return PLACE_PARLAY_LEG_ERROR_CODE.valueOf(text);
		} catch (IllegalArgumentException e) {
			return PLACE_PARLAY_LEG_ERROR_CODE.UNDEFINED;
		}
	}
}
