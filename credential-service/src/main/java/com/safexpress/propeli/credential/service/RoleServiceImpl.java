package com.safexpress.propeli.credential.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.dao.RoleRepository;
import com.safexpress.propeli.credential.domain.Role;
import com.safexpress.propeli.credential.entity.RoleEntity;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public long saveRole(Role role) {
		return roleRepository.save(modelMapper.map(role, RoleEntity.class)).getRoleId();
	}

	@Override
	public Role findRoleById(long roleId) {
		Optional<RoleEntity> role = roleRepository.findById(roleId);
		return modelMapper.map(role.get(), Role.class);
	}

}
