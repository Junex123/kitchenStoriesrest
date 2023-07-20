package com.project.kitchenstories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.kitchenstories.global.GlobalData;
import com.project.kitchenstories.model.Category;
import com.project.kitchenstories.model.bookingdetail;
import com.project.kitchenstories.model.contactus;
import com.project.kitchenstories.service.*;
@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	bookingService bookingservice;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	contactusService contactservice;
	
	@Autowired
	FoodService foodservice;
	
	@Autowired
	foodproService foodproservice;
	
	
	@GetMapping({"/" , "/home"})
	public String home(Model model) {
		model.addAttribute("cartCount" , GlobalData.cart1.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		return "index2";
	}
	
	@GetMapping({"Shop"})
	public String homeshop(Model model) {
		model.addAttribute("cartCount" , GlobalData.cart.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		return "index";
	}

	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("cartCount" , GlobalData.cart.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		model.addAttribute("categories" , categoryService.getAllCategories());
		model.addAttribute("products" , productService.getAllProducts());
		
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model , @PathVariable int id) {
		model.addAttribute("cartCount" , GlobalData.cart.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		model.addAttribute("categories" , categoryService.getAllCategories());
		model.addAttribute("products" , productService.getAllProductsByCategoryId(id));
		
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model , @PathVariable int id) {
		model.addAttribute("product" , productService.getProductById(id).get());
		model.addAttribute("cartCount" , GlobalData.cart.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		return "viewProduct";
	}
	
	@GetMapping({"/about"})
	public String about(Model model) {
		model.addAttribute("cartCount" , GlobalData.cart1.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		return "about";
	}
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("cartCount" , GlobalData.cart1.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		return "contact";
	}
    
	@PostMapping("/contact")
	public String postcontactAdd(@ModelAttribute("contact") contactus contact) {
		contactservice.addcontact(contact);
		return "redirect:/home";
	}

    

	@GetMapping({"/booking"})
	public String booking(Model model) {
		model.addAttribute("cartCount" , GlobalData.cart1.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		return "booking";
	}
	@PostMapping("/booking")
	public String postbookingAdd(@ModelAttribute("booking") bookingdetail booking) {
		bookingservice.addbooking(booking);
		return "redirect:/home";
	}

	@GetMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("cartCount" , GlobalData.cart1.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		model.addAttribute("food" , foodservice.getAllFood());
		model.addAttribute("foodproduct" , foodproservice.getAllfoodProducts());
		
		return "menu";
	}
	
	@GetMapping("/menu/food/{id}")
	public String shopByFood(Model model , @PathVariable int id) {
		model.addAttribute("cartCount" , GlobalData.cart1.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		model.addAttribute("food" , foodservice.getAllFood());
		model.addAttribute("foodproduct" , foodproservice.getAllProductsByFoodId(id));
		
		return "menu";
	}
	
	@GetMapping("/menu/viewfoodproduct/{id}")
	public String viewfoodProduct(Model model , @PathVariable int id) {
		model.addAttribute("foodproduct" , foodproservice.getfoodProductById(id).get());
		model.addAttribute("cartCount" , GlobalData.cart1.size());
		//  model.addAttribute("cartCount" , GlobalData.cart1.size());
		return "foodviewpro";
	}
	
	
	
}

