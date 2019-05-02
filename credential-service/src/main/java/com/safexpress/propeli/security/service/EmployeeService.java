package com.safexpress.propeli.security.service;

import java.util.Set;

import com.safexpress.propeli.security.domain.Employee;
import com.safexpress.propeli.security.domain.Role;

public interface EmployeeService {
	long saveEmployee(Employee employee);

	Set<Role> findRolesByUsername(String username);

}
