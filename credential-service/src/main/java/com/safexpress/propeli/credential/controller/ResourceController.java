package com.safexpress.propeli.credential.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.credential.dto.ResourceDto;
import com.safexpress.propeli.credential.service.ResourceService;

@RestController
@RequestMapping("/resource")
public class ResourceController extends AbstractController<ResourceDto> {

	@Autowired
	private ResourceService handler;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.resource-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.resource-v1+json" })
	public String saveResource(@RequestBody ResourceDto resourceDto) {
		return "Persisted successfully.";
	}

}
