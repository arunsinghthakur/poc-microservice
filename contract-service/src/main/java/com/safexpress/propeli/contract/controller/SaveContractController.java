package com.safexpress.propeli.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.contract.domain.Contract;
import com.safexpress.propeli.contract.dto.ContractDto;
import com.safexpress.propeli.contract.service.ContractService;

@RestController
@RequestMapping("/contract")
public class SaveContractController extends AbstractController<ContractDto> {

	@Autowired
	private ContractService handler;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.contract-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.contract-v1+json" })
	public String saveContract(@RequestBody ContractDto contractDto) {
		Contract contract = new Contract();
		contract.setDescription(contractDto.getDescription());
		handler.addContract(contract);
		return "Persisted successfully.";
	}

	@PostMapping(path = "/async", consumes = { "application/vnd.com.safexpress.propeli.contract-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.contract-v1+json" })
	public String saveContractAsync(@RequestBody ContractDto contractDto) {
		Contract contract = new Contract();
		contract.setDescription(contractDto.getDescription());
		handler.addContractAsync(contract);
		return "Persisted successfully.";
	}
}
