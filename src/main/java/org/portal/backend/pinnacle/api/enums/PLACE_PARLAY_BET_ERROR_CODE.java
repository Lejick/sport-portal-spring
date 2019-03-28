package org.portal.backend.pinnacle.api.enums;

public enum PLACE_PARLAY_BET_ERROR_CODE {

	ABOVE_MAX_BET_AMOUNT,
	ALL_BETTING_CLOSED,
	BELOW_MIN_BET_AMOUNT,
	BLOCKED_BETTING,
	BLOCKED_CLIENT,
	INSUFFICIENT_FUNDS,
	INVALID_COUNTRY,
	INVALID_LEGS,
	INVALID_ODDS_FORMAT,
	INVALID_ROUND_ROBIN_OPTIONS,
	ROUND_ROBIN_DISALLOWED,
	TOO_MANY_LEGS,
	TOO_FEW_LEGS,
	UNDEFINED;

	public String toAPI () {
		return this.name();
	}
	
	public static PLACE_PARLAY_BET_ERROR_CODE fromAPI (String text) {
		try {
			return PLACE_PARLAY_BET_ERROR_CODE.valueOf(text);
		} catch (IllegalArgumentException e) {
			return PLACE_PARLAY_BET_ERROR_CODE.UNDEFINED;
		}
	}
}
