package com.safexpress.propeli.contract.domain;

public class Contract {
	private Long contractId;
	private String description;

	public Contract() {
	}

	public Long getContractId() {
		return contractId;
	}

	public String getDescription() {
		return description;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
