package com.project.kitchenstories.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kitchenstories.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findAllByCategory_Id(int id);

}

