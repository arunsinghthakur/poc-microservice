package com.safexpress.propeli.credential.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Override
	public long saveUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
