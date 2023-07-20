package com.project.kitchenstories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kitchenstories.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

}