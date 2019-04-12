package com.safexpress.propeli.contract.entity;

import java.io.Serializable;

public class ContractEntity implements Serializable {

	private static final long serialVersionUID = -304423748041963147L;
	private long contractId;
	private String description;

	public ContractEntity() {
		super();
	}

	public ContractEntity(long contractId, String description) {
		super();
		this.contractId = contractId;
		this.description = description;
	}

	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
