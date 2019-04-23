package com.safexpress.propeli.credential.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.credential.dto.ResourcePermissionDto;
import com.safexpress.propeli.credential.service.ResourcePermissionService;

@RestController
@RequestMapping("/resource/permission")
public class ResourcePermissionController extends AbstractController<ResourcePermissionDto> {

	@Autowired
	private ResourcePermissionService handler;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.resourcepermission-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.resourcepermission-v1+json" })
	public String saveResourcePermission(@RequestBody ResourcePermissionDto resourcePermissionDto) {
		return "Persisted successfully.";
	}

}
