package com.project.kitchenstories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kitchenstories.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
		
	
	
}

