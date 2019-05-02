package com.safexpress.propeli.security.service;

import com.safexpress.propeli.security.domain.Permission;

public interface PermissionService {

	long savePermission(Permission permission);
	
	Permission findPermissionById(long permissionId);

}
