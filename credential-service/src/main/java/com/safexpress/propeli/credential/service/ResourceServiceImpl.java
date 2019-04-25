package com.safexpress.propeli.credential.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safexpress.propeli.credential.dao.ResourceRepository;
import com.safexpress.propeli.credential.domain.Resource;
import com.safexpress.propeli.credential.entity.ResourceEntity;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public long saveResource(Resource resource) {
		return resourceRepository.save(modelMapper.map(resource, ResourceEntity.class)).getResourceId();
	}

	@Override
	public Resource findResourceById(long resourceId) {
		Optional<ResourceEntity> resource = resourceRepository.findById(resourceId);
		return modelMapper.map(resource.get(), Resource.class);
	}

}
