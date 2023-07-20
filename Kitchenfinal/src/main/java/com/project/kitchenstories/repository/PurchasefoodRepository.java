package com.project.kitchenstories.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kitchenstories.model.purchaseFood;

public interface PurchasefoodRepository extends JpaRepository<purchaseFood, Integer> {

	

}