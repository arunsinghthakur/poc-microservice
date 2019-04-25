package com.safexpress.propeli.credential.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.dao.EmployeeRepository;
import com.safexpress.propeli.credential.domain.Employee;
import com.safexpress.propeli.credential.domain.Role;
import com.safexpress.propeli.credential.entity.EmployeeEntity;
import com.safexpress.propeli.security.util.PasswordUtil;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public long saveUser(Employee user) {
		user.setSalt(PasswordUtil.generateSalt());
		user.setPassword(PasswordUtil.encryptedPassword(user.getPassword(), user.getSalt()));
		return employeeRepository.save(modelMapper.map(user, EmployeeEntity.class)).getEmployeeId();
	}
	

	@Override
	public Set<Role> findRolesByUsername(String username) {
		EmployeeEntity user = employeeRepository.findUserByUsername(username);

		return user.getRoles().stream().map(role -> modelMapper.map(role, Role.class)).collect(Collectors.toSet());
	}

}
