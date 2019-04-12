package com.safexpress.propeli.contract.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.contract.domain.Contract;
import com.safexpress.propeli.contract.dto.ContractDto;
import com.safexpress.propeli.contract.handler.ContractHandler;

@RestController
@RequestMapping("/contract")
public class GetContractController extends AbstractController<ContractDto> {

	@Autowired
	private ContractHandler handler;

	@GetMapping(produces = "application/vnd.com.safexpress.propeli.contract-v1+json")
	public ContractDto getDummyContractV1() {
		return new ContractDto(System.currentTimeMillis(), "This is dummy contract version v1.");
	}

	@GetMapping(produces = "application/vnd.com.safexpress.propeli.contract-v2+json")
	public ContractDto getDummyContractV2() {
		return new ContractDto(System.currentTimeMillis(), "This is dummy contract version v2.");
	}

	@GetMapping(path = "/list", produces = { "application/vnd.com.safexpress.propeli.contract-v1+json" })
	public List<ContractDto> getAllContracts() {
		List<Contract> contracts = handler.getAllContract();

		return contracts.stream().map(con -> new ContractDto(con.getContractId(), con.getDescription()))
				.collect(Collectors.toList());
	}
}
