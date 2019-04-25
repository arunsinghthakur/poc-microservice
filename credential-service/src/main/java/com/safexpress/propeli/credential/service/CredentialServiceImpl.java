package com.safexpress.propeli.credential.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.dao.EmployeeRepository;
import com.safexpress.propeli.credential.domain.Employee;
import com.safexpress.propeli.credential.entity.EmployeeEntity;
import com.safexpress.propeli.security.util.PasswordUtil;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean isAuthenticated(Employee employee) {
		EmployeeEntity employeeEntity = employeeRepository.findUserByUsername(employee.getUsername());
		if (null != employeeEntity) {
			String inputEncryptedPwd = PasswordUtil.encryptedPassword(employee.getPassword(), employeeEntity.getSalt());
			if (employeeEntity.getPassword().equals(inputEncryptedPwd)) {
				return true;
			}
		}

		return false;
	}

}
