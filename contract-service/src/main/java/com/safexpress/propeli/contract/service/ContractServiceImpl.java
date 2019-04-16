package com.safexpress.propeli.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.contract.dao.ContractRepository;
import com.safexpress.propeli.contract.domain.BookingRequest;
import com.safexpress.propeli.contract.domain.Contract;
import com.safexpress.propeli.contract.entity.ContractEntity;
import com.safexpress.propeli.contract.integration.BookingIntegration;
import com.safexpress.propeli.contract.integration.redis.ContractCacheManager;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	@Qualifier("bookingRestIntegration")
	private BookingIntegration bookingRestIntegration;

	@Autowired
	@Qualifier("bookingKafkaIntegration")
	private BookingIntegration bookingKafkaIntegration;

	@Autowired
	@Qualifier("contractCacheManager")
	private ContractCacheManager contractCacheManager;

	@Override
	@Transactional
	public void addContract(Contract contract) {
		String bookingMsg = bookingRestIntegration.getBooking().get();
		ContractEntity contractEntity = new ContractEntity();
		contractEntity.setDescription(contract.getDescription() + bookingMsg);
		contractRepository.save(contractEntity);
		contractCacheManager.save(contractEntity);
	}

	@Override
	@Transactional
	public List<Contract> getAllContract() {
		List<ContractEntity> contractEntities = contractCacheManager.findAll();
		if (null == contractEntities || contractEntities.isEmpty()) {
			contractEntities = contractRepository.findAll();
		}
		return contractEntities.stream().map(con -> createContract(con)).collect(Collectors.toList());
	}

	private Contract createContract(ContractEntity contractEntity) {
		Contract contract = new Contract();
		contract.setContractId(contractEntity.getContractId());
		contract.setDescription(contractEntity.getDescription());
		return contract;
	}

	@Override
	@Transactional
	public void addContractAsync(Contract contract) {
		bookingKafkaIntegration.saveBooking(new BookingRequest(contract.getContractId(), contract.getDescription()));
		ContractEntity contractEntity = new ContractEntity();
		contractEntity.setDescription(contract.getDescription());
		contractRepository.save(contractEntity);
		contractCacheManager.save(contractEntity);
	}

}
