package com.safexpress.propeli.credential.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safexpress.propeli.credential.entity.ResourceEntity;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

}
