package com.safexpress.propeli.security.service;

import java.util.Set;

import com.safexpress.propeli.security.domain.ResourcePermission;

public interface ResourcePermissionService {

	long saveResourcePermission(ResourcePermission resourcePermission);

	Set<ResourcePermission> findResourcePermissionsByRoleId(long roleId);
}
