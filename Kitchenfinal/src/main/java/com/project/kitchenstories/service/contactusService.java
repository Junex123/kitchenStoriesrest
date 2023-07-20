package com.project.kitchenstories.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kitchenstories.model.bookingdetail;
import com.project.kitchenstories.model.contactus;
import com.project.kitchenstories.repository.bookingRepository;
import com.project.kitchenstories.repository.contactusRepository;

@Service
public class contactusService {
	@Autowired
	contactusRepository contactrepos;
	
	public List<contactus> getAllcontact(){
		
		return contactrepos.findAll();
		
	}
	
	public void addcontact(contactus contact) {
		
		contactrepos.save(contact);
	}
	
	public void removeconById(int id) {
		contactrepos.deleteById(id);
	}
	
	
	public Optional<contactus> getconById(int id) {
		
		return contactrepos.findById(id);
		
	}
}
