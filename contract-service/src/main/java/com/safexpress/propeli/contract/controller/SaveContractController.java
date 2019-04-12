package com.safexpress.propeli.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.contract.domain.Contract;
import com.safexpress.propeli.contract.dto.ContractDto;
import com.safexpress.propeli.contract.handler.ContractHandler;

@RestController
@RequestMapping("/contract")
public class SaveContractController extends AbstractController<ContractDto> {

	@Autowired
	private ContractHandler handler;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.contract-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.contract-v1+json" })
	public String saveContract(@RequestBody ContractDto contractDto) {
		handler.addContract(new Contract(contractDto.getContractId(), contractDto.getDescription()));
		return "Persisted successfully.";
	}

	@PostMapping(path = "/async", consumes = { "application/vnd.com.safexpress.propeli.contract-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.contract-v1+json" })
	public String saveContractAsync(@RequestBody ContractDto contractDto) {
		handler.addContractAsync(new Contract(contractDto.getContractId(), contractDto.getDescription()));
		return "Persisted successfully.";
	}
}
