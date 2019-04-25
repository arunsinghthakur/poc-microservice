package com.safexpress.propeli.credential.service;

import com.safexpress.propeli.credential.domain.Role;

public interface RoleService {

	long saveRole(Role role);

	Role findRoleById(long roleId);
}
