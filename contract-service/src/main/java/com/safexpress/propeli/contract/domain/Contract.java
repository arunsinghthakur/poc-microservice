package com.safexpress.propeli.contract.domain;

public class Contract {
	private long contractId;
	private String description;

	public Contract() {
		super();
	}

	public Contract(long contractId, String description) {
		super();
		this.contractId = contractId;
		this.description = description;
	}

	public long getContractId() {
		return contractId;
	}

	public String getDescription() {
		return description;
	}

}
