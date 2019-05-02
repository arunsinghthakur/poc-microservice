package com.safexpress.propeli.security.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.security.domain.ResourcePermission;
import com.safexpress.propeli.security.dto.ResourcePermissionDto;
import com.safexpress.propeli.security.service.ResourcePermissionService;

@RestController
@RequestMapping("/resource/permission")
public class ResourcePermissionController extends AbstractController<ResourcePermissionDto> {

	@Autowired
	private ResourcePermissionService service;

	@Autowired
	private ModelMapper mapper;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.resourcepermission-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.resourcepermission-v1+json" })
	public String saveResourcePermission(@RequestBody ResourcePermissionDto resourcePermissionDto) {
		service.saveResourcePermission(mapper.map(resourcePermissionDto, ResourcePermission.class));
		return "Persisted successfully.";
	}

}
