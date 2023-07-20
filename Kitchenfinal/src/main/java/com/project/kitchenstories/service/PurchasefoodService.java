package com.project.kitchenstories.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kitchenstories.model.Purchase;
import com.project.kitchenstories.model.purchaseFood;
import com.project.kitchenstories.repository.PurchaseRepository;
import com.project.kitchenstories.repository.PurchasefoodRepository;

@Service
public class PurchasefoodService {

@Autowired
PurchasefoodRepository purchaseRepository;
	
public List<purchaseFood> getAllPurchases(){
		
		return purchaseRepository.findAll();
		
	}

}
