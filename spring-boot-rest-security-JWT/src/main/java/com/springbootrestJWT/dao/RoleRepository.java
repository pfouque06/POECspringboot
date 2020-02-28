package com.springbootrestJWT.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootrestJWT.model.Role;
import com.springbootrestJWT.model.RoleName;



@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,Long> {
	
	Optional<Role> findByName(RoleName roleName);
	
	

}