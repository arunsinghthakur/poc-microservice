package com.safexpress.propeli.credential.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.dao.UserRepository;
import com.safexpress.propeli.credential.domain.User;
import com.safexpress.propeli.credential.entity.UserEntity;
import com.safexpress.propeli.security.util.SecurityUtil;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String authenticate(User user) {
		UserEntity userEntity = userRepository.findUserByEmail(user.getEmailAddress());
		if (null != userEntity) {
			String inputEncryptedPwd = SecurityUtil.encryptedPassword(user.getPassword(), userEntity.getSalt());
			if (userEntity.getPassword().equals(inputEncryptedPwd)) {
				// Token process
			}
			//throw exception
		}

		return null;
	}

}
