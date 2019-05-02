package com.safexpress.propeli.security.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.security.dao.EmployeeRepository;
import com.safexpress.propeli.security.domain.Employee;
import com.safexpress.propeli.security.domain.Role;
import com.safexpress.propeli.security.entity.EmployeeEntity;
import com.safexpress.propeli.security.util.PasswordUtil;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public long saveEmployee(Employee employee) {
		employee.setSalt(PasswordUtil.generateSalt());
		employee.setPassword(PasswordUtil.encryptedPassword(employee.getPassword(), employee.getSalt()));
		return employeeRepository.save(modelMapper.map(employee, EmployeeEntity.class)).getEmployeeId();
	}
	

	@Override
	public Set<Role> findRolesByUsername(String username) {
		EmployeeEntity employeeEntity = employeeRepository.findUserByUsername(username);

		return employeeEntity.getRoles().stream().map(role -> modelMapper.map(role, Role.class)).collect(Collectors.toSet());
	}

}
