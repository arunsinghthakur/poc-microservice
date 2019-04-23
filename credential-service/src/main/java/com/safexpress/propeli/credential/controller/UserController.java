package com.safexpress.propeli.credential.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safexpress.propeli.common.controller.AbstractController;
import com.safexpress.propeli.credential.dto.UserDto;
import com.safexpress.propeli.credential.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<UserDto> {

	@Autowired
	private UserService service;

	@PostMapping(consumes = { "application/vnd.com.safexpress.propeli.user-v1+json" }, produces = {
			"application/vnd.com.safexpress.propeli.user-v1+json" })
	public String saveUser(@RequestBody UserDto userDto) {
		return "Persisted successfully.";
	}

}
