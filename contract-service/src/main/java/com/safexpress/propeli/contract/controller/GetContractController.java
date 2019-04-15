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
import com.safexpress.propeli.contract.service.ContractService;

@RestController
@RequestMapping("/contract")
public class GetContractController extends AbstractController<ContractDto> {

	@Autowired
	private ContractService handler;

	@GetMapping(produces = "application/vnd.com.safexpress.propeli.contract-v1+json")
	public ContractDto getDummyContractV1() {
		ContractDto dto = new ContractDto();
		dto.setContractId(System.currentTimeMillis());
		dto.setDescription("This is dummy contract version v1.");
		return dto;
	}

	@GetMapping(produces = "application/vnd.com.safexpress.propeli.contract-v2+json")
	public ContractDto getDummyContractV2() {
		ContractDto dto = new ContractDto();
		dto.setContractId(System.currentTimeMillis());
		dto.setDescription("This is dummy contract version v2.");
		return dto;
	}

	@GetMapping(path = "/list", produces = { "application/vnd.com.safexpress.propeli.contract-v1+json" })
	public List<ContractDto> getAllContracts() {
		List<Contract> contracts = handler.getAllContract();

		return contracts.stream().map(con -> createContractDto(con)).collect(Collectors.toList());
	}

	private ContractDto createContractDto(Contract con) {
		ContractDto dto = new ContractDto();
		dto.setContractId(con.getContractId());
		dto.setDescription(con.getDescription());
		return dto;
	}
}
