package com.safexpress.propeli.contract.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.safexpress.propeli.contract.entity.ContractEntity;

public class ContractRowMapper implements RowMapper<ContractEntity> {

	@Override
	public ContractEntity mapRow(ResultSet row, int rowNum) throws SQLException {
		return new ContractEntity(row.getLong("contractId"), row.getString("description"));
	}
}
