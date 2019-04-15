package com.safexpress.propeli.contract.service;

import java.util.List;

import com.safexpress.propeli.contract.domain.Contract;

public interface ContractService {
	void addContract(Contract contract);

	void addContractAsync(Contract contract);

	List<Contract> getAllContract();
}
