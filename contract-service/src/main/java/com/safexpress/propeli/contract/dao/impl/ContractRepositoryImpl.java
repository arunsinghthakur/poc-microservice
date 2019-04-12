package com.safexpress.propeli.contract.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.safexpress.propeli.contract.dao.ContractRepository;
import com.safexpress.propeli.contract.dao.mapper.ContractRowMapper;
import com.safexpress.propeli.contract.entity.ContractEntity;

@Repository
public class ContractRepositoryImpl implements ContractRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(ContractEntity entity) {
		String sql = "INSERT INTO contract (contractId, description) values (?, ?)";
		jdbcTemplate.update(sql, entity.getContractId(), entity.getDescription());
	}

	@Override
	public List<ContractEntity> getAll() {
		String sql = "SELECT * FROM contract";
		RowMapper<ContractEntity> rowMapper = new ContractRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public ContractEntity get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContractEntity update(ContractEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
