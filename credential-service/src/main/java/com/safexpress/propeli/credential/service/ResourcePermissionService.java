package com.safexpress.propeli.credential.service;

import java.util.Set;

import com.safexpress.propeli.credential.domain.ResourcePermission;

public interface ResourcePermissionService {

	long saveResourcePermission(ResourcePermission resourcePermission);

	Set<ResourcePermission> findResourcePermissionsByRoleId(long roleId);
}
