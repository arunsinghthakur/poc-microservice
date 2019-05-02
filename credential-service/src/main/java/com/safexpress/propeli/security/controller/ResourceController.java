package com.safexpress.propeli.security.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.security.domain.Resource;
import com.safexpress.propeli.security.dto.ResourceDto;
import com.safexpress.propeli.security.service.ResourceService;

@RestController
@RequestMapping("/resource")
public class ResourceController extends AbstractController<ResourceDto> {

	@Autowired
	private ResourceService service;
	
	@Autowired
	private ModelMapper mapper;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.resource-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.resource-v1+json" })
	public String saveResource(@RequestBody ResourceDto resourceDto) {
		service.saveResource(mapper.map(resourceDto, Resource.class));
		return "Persisted successfully.";
	}

}
