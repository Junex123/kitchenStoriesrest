package com.project.kitchenstories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kitchenstories.model.bookingdetail;


public interface bookingRepository extends JpaRepository<bookingdetail,Integer> {

}
