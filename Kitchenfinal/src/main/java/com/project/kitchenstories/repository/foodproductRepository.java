package com.project.kitchenstories.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kitchenstories.model.Foodproduct;


public interface foodproductRepository extends JpaRepository <Foodproduct,Integer> {
	List<Foodproduct> findAllByFood_Id(int id);
}
