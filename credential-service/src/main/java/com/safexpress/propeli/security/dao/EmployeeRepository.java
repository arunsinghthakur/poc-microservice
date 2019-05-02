package com.safexpress.propeli.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.safexpress.propeli.security.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	@Query(value = "select * from EmployeeEntity u where u.username = :username" , nativeQuery = true)
	public EmployeeEntity findUserByUsername(@Param("username") String username);
}
