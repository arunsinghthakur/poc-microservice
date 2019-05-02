package com.safexpress.propeli.security.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.security.domain.Employee;
import com.safexpress.propeli.security.dto.EmployeeDto;
import com.safexpress.propeli.security.service.EmployeeService;

@RestController
@RequestMapping("/user")
public class EmployeeController extends AbstractController<EmployeeDto> {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private ModelMapper mapper;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.employee-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.employee-v1+json" })
	public String saveUser(@RequestBody EmployeeDto employeeDto) {
		service.saveEmployee(mapper.map(employeeDto, Employee.class));
		return "Persisted successfully.";
	}

}
