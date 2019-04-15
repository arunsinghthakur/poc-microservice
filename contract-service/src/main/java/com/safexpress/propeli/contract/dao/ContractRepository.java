package com.safexpress.propeli.contract.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safexpress.propeli.contract.entity.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

}
