package com.safexpress.propeli.credential.service;

import com.safexpress.propeli.credential.domain.Resource;

public interface ResourceService {

	long saveResource(Resource resource);

	Resource findResourceById(long resourceId);

}
