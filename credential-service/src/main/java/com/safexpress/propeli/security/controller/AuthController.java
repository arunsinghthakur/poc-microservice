package com.safexpress.propeli.security.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.security.domain.Credential;
import com.safexpress.propeli.security.dto.CredentialDto;
import com.safexpress.propeli.security.dto.EmployeeDto;
import com.safexpress.propeli.security.service.CredentialService;

@RestController
public class AuthController extends AbstractController<EmployeeDto> {

	@Autowired
	private CredentialService service;

	@Autowired
	private ModelMapper mapper;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.credential-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.credential-v1+json" })
	@RequestMapping("/auth/token")
	public String login(@RequestHeader("originUserType") String originUserType,
			@RequestBody CredentialDto credentialDto) {
		Credential cred = mapper.map(credentialDto, Credential.class);
		cred.setUserType(originUserType);
		return service.login(cred);
	}

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.credential-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.credential-v1+json" })
	@RequestMapping("/logout")
	public String logout(@RequestParam("token") String token) {
		service.logout(token);
		return "Logout";
	}
}
