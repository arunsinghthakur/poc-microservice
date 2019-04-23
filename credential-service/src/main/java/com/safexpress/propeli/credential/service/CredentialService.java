package com.safexpress.propeli.credential.service;

import com.safexpress.propeli.credential.domain.Token;
import com.safexpress.propeli.credential.domain.User;

public interface CredentialService {
	Token authenticate(User user);
}
