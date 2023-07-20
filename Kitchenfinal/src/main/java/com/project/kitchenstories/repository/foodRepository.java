package com.project.kitchenstories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kitchenstories.model.Food;

public interface foodRepository extends JpaRepository<Food,Integer> {

}
