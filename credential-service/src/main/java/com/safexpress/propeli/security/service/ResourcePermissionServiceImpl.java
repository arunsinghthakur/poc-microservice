package com.safexpress.propeli.security.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.security.dao.ResourcePermissionRepository;
import com.safexpress.propeli.security.domain.ResourcePermission;
import com.safexpress.propeli.security.entity.ResourcePermissionEntity;

@Service
@Transactional
public class ResourcePermissionServiceImpl implements ResourcePermissionService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ResourcePermissionRepository permissionRepository;
	
	@Override
	public long saveResourcePermission(ResourcePermission resourcePermission) {
		return permissionRepository.save(modelMapper.map(resourcePermission, ResourcePermissionEntity.class)).getResourcePermissionId();
	}
	
	@Override
	public Set<ResourcePermission> findResourcePermissionsByRoleId(long roleId) {
		return permissionRepository.findResourcePermissionsByRoleId(roleId).stream()
				.map(r -> modelMapper.map(r, ResourcePermission.class)).collect(Collectors.toSet());
	}

}
