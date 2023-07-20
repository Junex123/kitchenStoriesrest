package com.project.kitchenstories.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kitchenstories.model.Food;
import com.project.kitchenstories.repository.foodRepository;

@Service
public class FoodService {

	@Autowired
	foodRepository foodrepos;
	
	public List<Food> getAllFood(){
		
		return foodrepos.findAll();
		
	}
	
	public void addFood(Food food) {
		
		foodrepos.save(food);
	}
	
	public void removeFoodById(int id) {
		foodrepos.deleteById(id);
	}
	
	
	public Optional<Food> getFoodById(int id) {
		
		return foodrepos.findById(id);
		
	}
	
}
