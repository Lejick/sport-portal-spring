package org.portal.backend.pinnacle.api.dataobjects;


import org.portal.backend.pinnacle.api.Json;
import org.portal.backend.pinnacle.api.PinnacleException;

import java.math.BigDecimal;

public class ClientBalance extends AbstractDataObject {

	private BigDecimal availableBalance;

	public BigDecimal availableBalance() {
		return this.availableBalance;
	}

	private BigDecimal outstandingTransactions;

	public BigDecimal outstandingTransactions() {
		return this.outstandingTransactions;
	}

	private BigDecimal givenCredit;

	public BigDecimal givenCredit() {
		return this.givenCredit;
	}

	private String currency;

	public String currency() {
		return this.currency;
	}

	private ClientBalance(Json json) {
		this.availableBalance = json.getAsBigDecimal("availableBalance").orElse(null);
		this.outstandingTransactions = json.getAsBigDecimal("outstandingTransactions").orElse(null);
		this.givenCredit = json.getAsBigDecimal("givenCredit").orElse(null);
		this.currency = json.getAsString("currency").orElse(null);
		this.checkRequiredKeys();
	}

	private ClientBalance() {
		this.isEmpty = true;
	}

	public static ClientBalance parse(String jsonText) throws PinnacleException {
		return jsonText.equals("{}") ? new ClientBalance() : new ClientBalance(Json.of(jsonText));
	}

	@Override
	boolean hasRequiredKeyWithNull() {
		return this.availableBalance == null || this.outstandingTransactions == null || this.givenCredit == null
				|| this.currency == null;
	}

	@Override
	public String toString() {
		return "ClientBalance [availableBalance=" + availableBalance + ", outstandingTransactions="
				+ outstandingTransactions + ", givenCredit=" + givenCredit + ", currency=" + currency + "]";
	}
}
