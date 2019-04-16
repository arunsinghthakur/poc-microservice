package com.safexpress.propeli.contract.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class ContractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -491760812366942815L;


	@Id
	@Column(name = "contractid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contractId;

	@Column(name = "description")
	private String description;

	public ContractEntity() {
	}

	public ContractEntity(String description) {
		this.description = description;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
