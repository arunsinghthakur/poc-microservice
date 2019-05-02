package com.safexpress.propeli.security.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.security.domain.Permission;
import com.safexpress.propeli.security.dto.PermissionDto;
import com.safexpress.propeli.security.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService service;

	@Autowired
	private ModelMapper mapper;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.permission-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.permission-v1+json" })
	public String savePermission(@RequestBody PermissionDto permissionDto) {
		service.savePermission(mapper.map(permissionDto, Permission.class));
		return "Persisted successfully.";
	}

}
