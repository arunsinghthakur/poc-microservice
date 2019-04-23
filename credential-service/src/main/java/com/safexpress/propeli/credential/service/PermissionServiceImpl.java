package com.safexpress.propeli.credential.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.domain.Permission;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Override
	public long savePermission(Permission permission) {
		// TODO Auto-generated method stub
		return 0;
	}

}
