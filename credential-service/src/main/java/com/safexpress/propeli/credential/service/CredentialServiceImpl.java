package com.safexpress.propeli.credential.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.dao.UserRepository;
import com.safexpress.propeli.credential.domain.Token;
import com.safexpress.propeli.credential.domain.User;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Token authenticate(User user) {
		return null;
	}

}
