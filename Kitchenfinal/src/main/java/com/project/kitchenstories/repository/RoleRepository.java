package com.project.kitchenstories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kitchenstories.model.Role;



public interface RoleRepository extends JpaRepository<Role, Integer> {

	
}

