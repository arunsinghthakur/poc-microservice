package com.safexpress.propeli.credential.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.safexpress.propeli.credential.entity.ResourcePermissionEntity;

@Repository
public interface ResourcePermissionRepository extends JpaRepository<ResourcePermissionEntity, Long> {

	@Query(value = "select * from ResourcePermissionEntity u where u.roleId = :roleId", nativeQuery = true)
	public List<ResourcePermissionEntity> findResourcePermissionsByRoleId(@Param("roleId") long roleId);
}
