package com.safexpress.propeli.contract.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractDto implements Serializable {
	private static final long serialVersionUID = -7403552226940563012L;

	@JsonProperty
	private Long contractId;

	@JsonProperty
	private String description;

	public ContractDto() {
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
