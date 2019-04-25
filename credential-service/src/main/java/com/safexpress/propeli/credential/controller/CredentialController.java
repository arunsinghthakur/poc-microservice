package com.safexpress.propeli.credential.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.credential.dto.EmployeeDto;
import com.safexpress.propeli.credential.service.CredentialService;

@RestController
@RequestMapping("/credential")
public class CredentialController extends AbstractController<EmployeeDto> {

	@Autowired
	private CredentialService handler;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.credential-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.credential-v1+json" })
	public String authtenticate(@RequestBody EmployeeDto userDto) {
		return null;
	}

}
