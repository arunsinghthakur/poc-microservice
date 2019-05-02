package com.safexpress.propeli.security.service;

import com.safexpress.propeli.security.domain.Role;

public interface RoleService {

	long saveRole(Role role);

	Role findRoleById(long roleId);
}
