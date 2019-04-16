package com.safexpress.propeli.contract.integration.redis;

import java.util.List;

import com.safexpress.propeli.contract.entity.ContractEntity;
import com.safexpress.propeli.redis.manager.AbstractCacheManager;

public abstract class ContractCacheManager extends AbstractCacheManager<ContractEntity> {

	public abstract void save(ContractEntity data);

	public abstract ContractEntity get(Long key);

	public abstract List<ContractEntity> findAll();
}
