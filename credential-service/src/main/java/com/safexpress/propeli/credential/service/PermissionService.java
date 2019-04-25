package com.safexpress.propeli.credential.service;

import com.safexpress.propeli.credential.domain.Permission;

public interface PermissionService {

	long savePermission(Permission permission);
	
	Permission findPermissionById(long permissionId);

}
