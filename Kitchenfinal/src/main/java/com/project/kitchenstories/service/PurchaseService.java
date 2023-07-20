package com.project.kitchenstories.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kitchenstories.model.Purchase;
import com.project.kitchenstories.repository.PurchaseRepository;

@Service
public class PurchaseService {

@Autowired
PurchaseRepository purchaseRepository;
	
public List<Purchase> getAllPurchases(){
		
		return purchaseRepository.findAll();
		
	}

}
