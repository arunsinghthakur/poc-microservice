package com.safexpress.propeli.contract.handler.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.booking.domain.BookingRequest;
import com.safexpress.propeli.contract.dao.ContractRepository;
import com.safexpress.propeli.contract.domain.Contract;
import com.safexpress.propeli.contract.entity.ContractEntity;
import com.safexpress.propeli.contract.handler.ContractHandler;
import com.safexpress.propeli.contract.integration.BookingIntegration;

@Service
@Transactional
public class ContractHandlerImpl implements ContractHandler {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	@Qualifier("bookingRestIntegration")
	private BookingIntegration bookingRestIntegration;
	
	@Autowired
	@Qualifier("bookingKafkaIntegration")
	private BookingIntegration bookingKafkaIntegration;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public void addContract(Contract contract) {
		String bookingMsg = bookingRestIntegration.getBooking().get();
		ContractEntity contractEntity = mapper.map(contract, ContractEntity.class);
		contractEntity.setDescription(contractEntity.getDescription() + bookingMsg);
		contractRepository.save(contractEntity);
	}

	@Override
	@Transactional
	public List<Contract> getAllContract() {
		List<ContractEntity> contractEntities = contractRepository.getAll();
		return contractEntities.stream().map(con -> new Contract(con.getContractId(), con.getDescription()))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void addContractAsync(Contract contract) {
		bookingKafkaIntegration.saveBooking(new BookingRequest(contract.getContractId(), contract.getDescription()));
		ContractEntity contractEntity = mapper.map(contract, ContractEntity.class);
		contractEntity.setDescription(contractEntity.getDescription());
		contractRepository.save(contractEntity);
	}

}
