package com.safexpress.propeli.security.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.cache.manager.CredentialCacheManager;
import com.safexpress.propeli.security.dao.EmployeeRepository;
import com.safexpress.propeli.security.domain.Credential;
import com.safexpress.propeli.security.domain.CustomClaims;
import com.safexpress.propeli.security.domain.CustomUsernamePasswordToken;
import com.safexpress.propeli.security.domain.Employee;
import com.safexpress.propeli.security.entity.EmployeeEntity;
import com.safexpress.propeli.security.util.JWTUtil;
import com.safexpress.propeli.security.util.PasswordUtil;

import io.jsonwebtoken.Claims;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CredentialCacheManager cacheManager;

	@Override
	public boolean isAuthenticated(Employee employee) {
		EmployeeEntity employeeEntity = employeeRepository.findUserByUsername(employee.getUsername());
		if (null != employeeEntity) {
			String inputEncryptedPwd = PasswordUtil.encryptedPassword(employee.getPassword(), employeeEntity.getSalt());
			String encryptedPwd = employeeEntity.getPassword();
			if (encryptedPwd.trim().equals(inputEncryptedPwd.trim())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String login(Credential credential) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			CustomUsernamePasswordToken token = new CustomUsernamePasswordToken(credential.getUsername(),
					credential.getPassword(), credential.isRememberMe(), credential.getUserType());
			subject.login(token);
		}
		return createToken(credential.getUsername(), (String) subject.getSession().getId());
	}

	@Override
	public void logout(String token) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		removeToken(token);
	}

	private String createToken(String username, String jSessionId) {
		Cache<Object, Object> tokenCache = cacheManager.getCache(username);
		String token = (String) tokenCache.get(username);
		if (null == token) {
			token = JWTUtil.createJWT(username, new CustomClaims(jSessionId));
			tokenCache.put(username, token);
		}
		return token;
	}

	private void removeToken(String token) {
		try {
			Claims claims = JWTUtil.parseJWT(token);
			Cache<Object, Object> tokenCache = cacheManager.getCache(claims.getSubject());
			tokenCache.remove(claims.getSubject());
		} catch (Exception e) {
			// add log.
			e.printStackTrace();
		}
	}

}
