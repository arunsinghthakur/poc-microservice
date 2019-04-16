package com.safexpress.propeli.contract.integration.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safexpress.propeli.contract.entity.ContractEntity;

@Service("contractCacheManager")
public class ContractCacheManagerImpl extends ContractCacheManager {
	private static final String KEY = "CONTRACT";

	@Override
	public void save(ContractEntity data) {
		putMap(KEY, data.getContractId(), data);
	}

	@Override
	public ContractEntity get(Long key) {
		return getMapAsSingleEntry(KEY, key);
	}

	@Override
	public List<ContractEntity> findAll() {
		return new ArrayList<ContractEntity>(getMapAsAll(KEY).values());
	}

}
