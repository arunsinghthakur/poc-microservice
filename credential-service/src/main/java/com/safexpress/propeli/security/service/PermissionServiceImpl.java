package com.safexpress.propeli.security.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.security.dao.PermissionRepository;
import com.safexpress.propeli.security.domain.Permission;
import com.safexpress.propeli.security.entity.PermissionEntity;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public long savePermission(Permission permission) {
		return permissionRepository.save(modelMapper.map(permission, PermissionEntity.class)).getPermissionId();
	}

	@Override
	public Permission findPermissionById(long permissionId) {
		Optional<PermissionEntity> permission = permissionRepository.findById(permissionId);
		return modelMapper.map(permission.get(), Permission.class);
	}

}
