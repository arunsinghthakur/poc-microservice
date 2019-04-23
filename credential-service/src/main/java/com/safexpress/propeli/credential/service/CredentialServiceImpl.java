package com.safexpress.propeli.credential.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.domain.Token;
import com.safexpress.propeli.credential.domain.User;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

	@Override
	public Token authenticate(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
