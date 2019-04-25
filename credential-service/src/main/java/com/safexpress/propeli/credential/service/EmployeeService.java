package com.safexpress.propeli.credential.service;

import java.util.Set;

import com.safexpress.propeli.credential.domain.Employee;
import com.safexpress.propeli.credential.domain.Role;

public interface EmployeeService {
	long saveUser(Employee user);

	Set<Role> findRolesByUsername(String username);

}
