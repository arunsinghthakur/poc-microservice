package com.safexpress.propeli.contract.integration.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.safexpress.propeli.contract.entity.ContractEntity;
import com.safexpress.propeli.redis.manager.AbstractCacheManager;

@Configuration
public class ContractCacheManagerImpl extends AbstractCacheManager<ContractEntity> {

}
