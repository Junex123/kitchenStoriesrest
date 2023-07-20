package com.project.kitchenstories.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kitchenstories.model.Foodproduct;
import com.project.kitchenstories.repository.foodproductRepository;

@Service
public class foodproService {

	@Autowired
	foodproductRepository foodproductRepository;
	
	public List<Foodproduct> getAllfoodProducts(){
		
		return foodproductRepository.findAll();
		
	}

	public void addfoodProduct(Foodproduct product) {
		
		foodproductRepository.save(product);
		
	}
	
	public void removefoodProductById(int id) {
		
		foodproductRepository.deleteById(id);
	}
	
	public Optional<Foodproduct> getfoodProductById(int id){
		return foodproductRepository.findById(id);
	}
	
	public List<Foodproduct> getAllProductsByFoodId(int id){
		return foodproductRepository.findAllByFood_Id(id);
		
	}
	
	
	
}
