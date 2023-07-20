package com.project.kitchenstories.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kitchenstories.model.Category;
import com.project.kitchenstories.model.bookingdetail;
import com.project.kitchenstories.repository.CategoryRepository;
import com.project.kitchenstories.repository.bookingRepository;

@Service
public class bookingService {
	@Autowired
	bookingRepository bookingrepos;
	
	public List<bookingdetail> getAllbooking(){
		
		return bookingrepos.findAll();
		
	}
	
	public void addbooking(bookingdetail booking) {
		
		bookingrepos.save(booking);
	}
	
	public void removebookingById(int id) {
		bookingrepos.deleteById(id);
	}
	
	
	public Optional<bookingdetail> getCatById(int id) {
		
		return bookingrepos.findById(id);
		
	}
}
