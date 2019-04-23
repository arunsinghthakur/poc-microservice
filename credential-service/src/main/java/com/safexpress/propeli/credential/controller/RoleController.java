package com.safexpress.propeli.credential.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.credential.dto.RoleDto;
import com.safexpress.propeli.credential.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController extends AbstractController<RoleDto> {

	@Autowired
	private RoleService service;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.role-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.role-v1+json" })
	public String saveRole(@RequestBody RoleDto roleDto) {
		return "Persisted successfully.";
	}

}
