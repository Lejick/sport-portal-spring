package org.portal.back.pinnacle.api.enums;

public enum PLACE_TEASER_BET_LEG_ERROR_CODE {

	CANNOT_TEASER_LIVE_GAME,
	CHECK_TEASER_ERROR,
	INVALID_EVENT,
	INVALID_LEG_BET,
	INVALID_LEG_BET_TYPE,
	LINE_CHANGED,
	LINE_DOES_NOT_BELONG_TO_EVENT,
	OFFLINE_EVENT,
	PAST_CUTOFFTIME,
	POINTS_ARE_NOT_ADJUSTED,
	SYSTEM_ERROR_1,
	SYSTEM_ERROR_2,
	UNKNOWN,
	WAGER_DATA_MISSING,
	UNDEFINED;

	public String toAPI () {
		return this.name();
	}
	
	public static PLACE_TEASER_BET_LEG_ERROR_CODE fromAPI (String text) {
		try {
			return PLACE_TEASER_BET_LEG_ERROR_CODE.valueOf(text);
		} catch (IllegalArgumentException e) {
			return PLACE_TEASER_BET_LEG_ERROR_CODE.UNDEFINED;
		}
	}
}
