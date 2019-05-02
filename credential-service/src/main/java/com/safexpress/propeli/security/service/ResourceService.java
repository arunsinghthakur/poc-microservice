package com.safexpress.propeli.security.service;

import com.safexpress.propeli.security.domain.Resource;

public interface ResourceService {

	long saveResource(Resource resource);

	Resource findResourceById(long resourceId);

}
