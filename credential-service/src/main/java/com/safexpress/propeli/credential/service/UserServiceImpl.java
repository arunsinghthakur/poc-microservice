package com.safexpress.propeli.credential.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.dao.UserRepository;
import com.safexpress.propeli.credential.domain.User;
import com.safexpress.propeli.credential.entity.UserEntity;
import com.safexpress.propeli.security.util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public long saveUser(User user) {
		user.setSalt(PasswordUtil.generateSalt());
		user.setPassword(PasswordUtil.encryptedPassword(user.getPassword(), user.getSalt()));
		return userRepository.save(modelMapper.map(user, UserEntity.class)).getEmployeeId();
	}

}
