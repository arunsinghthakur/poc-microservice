package com.safexpress.propeli.credential.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safexpress.propeli.credential.entity.ResourcePermissionEntity;

@Repository
public interface ResourcePermissionRepository extends JpaRepository<ResourcePermissionEntity, Long> {

}
