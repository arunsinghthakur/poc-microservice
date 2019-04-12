package com.safexpress.propeli.contract.handler;

import java.util.List;

import com.safexpress.propeli.contract.domain.Contract;

public interface ContractHandler {
	void addContract(Contract contract);

	void addContractAsync(Contract contract);

	List<Contract> getAllContract();
}
