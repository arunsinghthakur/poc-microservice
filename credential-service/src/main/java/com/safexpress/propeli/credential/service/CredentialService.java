package com.safexpress.propeli.credential.service;

import com.safexpress.propeli.credential.domain.User;

public interface CredentialService {
	String authenticate(User user);
}
