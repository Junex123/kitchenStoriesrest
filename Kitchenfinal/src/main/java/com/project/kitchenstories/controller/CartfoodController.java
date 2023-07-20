package com.project.kitchenstories.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.kitchenstories.global.GlobalData;
import com.project.kitchenstories.model.Foodproduct;
import com.project.kitchenstories.model.Product;
import com.project.kitchenstories.model.Purchase;
import com.project.kitchenstories.model.User;
import com.project.kitchenstories.model.purchaseFood;
import com.project.kitchenstories.repository.PurchaseRepository;
import com.project.kitchenstories.repository.PurchasefoodRepository;
import com.project.kitchenstories.repository.UserRepository;
import com.project.kitchenstories.service.ProductService;
import com.project.kitchenstories.service.foodproService;

@Controller
public class CartfoodController {
	@Autowired
	foodproService foodproservice;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PurchasefoodRepository purchasefoodRepository;
	
	@GetMapping("/addfoodToCart/{id}")
	public String addToCart(@PathVariable int id) {
	GlobalData.cart1.add(foodproservice.getfoodProductById(id).get());
		return "redirect:/menu";
	}
	
	@GetMapping("/foodcart")
	public String cartGet(Model model) {
	model.addAttribute("cartCount" , GlobalData.cart1.size());
	model.addAttribute("total" , GlobalData.cart1.stream().mapToDouble(Foodproduct::getPrice).sum());
	model.addAttribute("cart" , GlobalData.cart1);
		return "foodcart";
		
		
	}
	
	@GetMapping("/foodcart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
	GlobalData.cart1.remove(index);
		return "redirect:/foodcart";
	}
	
	@GetMapping("/foodcheckout")
	public String checkout(Model model) {
	model.addAttribute("total" , GlobalData.cart1.stream().mapToDouble(Foodproduct::getPrice).sum());
		return "foodcheckout";
	}
	
	
	@PostMapping("/foodpayNow")
	public String orderConfirmation(Model model) {
		model.addAttribute("total" , GlobalData.cart1.stream().mapToDouble(Foodproduct::getPrice).sum());
//		model.addAttribute("total" , GlobalData.cart1.stream().mapToDouble(Foodproduct::getPrice).sum());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = auth.getName();
		
		List<purchaseFood>purchaseList = new ArrayList<purchaseFood>();
		//System.out.println(currentPrincipalName);
		User user = userRepository.findUserByEmail(currentPrincipalName).get();
		for(Foodproduct product: GlobalData.cart1) {
			purchaseFood purchase = new purchaseFood();
			//System.out.println(product.getId() + " " + product.getName());
			purchase.setProductId(product.getId());
			purchase.setUserId(user.getId());
			purchase.setOrderDate(LocalDate.now());
			purchaseList.add(purchase);
		}

		
		int n = 10000 + new Random().nextInt(90000);
		model.addAttribute("Reciept" , n);
		model.addAttribute("foodproduct" , GlobalData.cart1);
		purchasefoodRepository.saveAll(purchaseList);
		
		return "foodorderPlaced";
	}
	
}

