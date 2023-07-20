package com.project.kitchenstories.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.kitchenstories.dto.FoodDTO;
import com.project.kitchenstories.dto.ProductDTO;
import com.project.kitchenstories.global.*;
import com.project.kitchenstories.model.*;
import com.project.kitchenstories.repository.*;
import com.project.kitchenstories.service.CategoryService;
import com.project.kitchenstories.service.FoodService;
import com.project.kitchenstories.service.ProductService;
import com.project.kitchenstories.service.PurchaseService;
import com.project.kitchenstories.service.PurchasefoodService;
import com.project.kitchenstories.service.foodproService;
@Controller
public class AdminController {

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	bookingRepository bookingrepos;
	
	@Autowired
	contactusRepository contactrepos;
	
	@Autowired
	foodproductRepository profoodrepos;
	
	@Autowired
	foodRepository foodrepos;
	
	@Autowired
	foodproService foodproservice;
	
	@Autowired
	FoodService foodservice;
	
	@Autowired
	PurchasefoodService purfoodservice;
	
	@GetMapping("/admin")
	public String adminHome() {
		
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories" , categoryService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/admin/food")
	public String getfood(Model model) {
		model.addAttribute("food" , foodservice.getAllFood());
		return "food";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category" , new Category());
		return "categoriesAdd";
	}
	
	@GetMapping("/admin/food/add")
	public String getfoodAdd(Model model) {
		model.addAttribute("food" , new Food());
		return "foodAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@PostMapping("/admin/food/add")
	public String postfoodAdd(@ModelAttribute("food") Food food) {
		foodservice.addFood(food);
		return "redirect:/admin/food";
	}
	
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		
		categoryService.removeCategoryById(id);
		
		return "redirect:/admin/categories";
	}
	

	@GetMapping("/admin/food/delete/{id}")
	public String deletefood(@PathVariable int id) {
		
		foodservice.removeFoodById(id);
		
		return "redirect:/admin/food";
	}
	
	
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id , Model model) {
		
		Optional<Category> category = categoryService.getCatById(id);
		
		if(category.isPresent()) {
			model.addAttribute("category" , category.get());
			return "categoriesAdd";
		}else {
			return "404";
		}
		}
	
	@GetMapping("/admin/food/update/{id}")
	public String updatefood(@PathVariable int id , Model model) {
		
		Optional<Food> food = foodservice.getFoodById(id);
		
		if(food.isPresent()) {
			model.addAttribute("food" , food.get());
			return "foodAdd";
		}else {
			return "404";
		}
		}
	
// Product Section and food product section
	

	@GetMapping("/admin/products")
	public String showProducts(Model model) {
		
		model.addAttribute("products" , productService.getAllProducts());
		
		return "products";
	}
	
	@GetMapping("/admin/foodproduct")
	public String showProductfood(Model model) {
		
		model.addAttribute("foodproduct" , foodproservice.getAllfoodProducts());
		
		return "foodproduct";
	}
	
	
	
	
	@GetMapping("/admin/products/add")
	public String addProduct(Model model) {
		
		model.addAttribute("productDTO" , new ProductDTO());
		model.addAttribute("categories" , categoryService.getAllCategories());
		return "productsAdd";
	}
	
	@GetMapping("/admin/foodproduct/add")
	public String addProductfood(Model model) {
		
		model.addAttribute("FoodDTO" , new FoodDTO());
		model.addAttribute("food" , foodservice.getAllFood());
		return "foodproadd";
	}
	
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute ("productDTO") ProductDTO productDTO , 
								@RequestParam("productImage") MultipartFile file,
								@RequestParam("imgName") String imgName) throws IOException {
			
		Product product = new Product();
		
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCatById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			
			imageUUID = imgName;
		}
		
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		
		return "redirect:/admin/products";
		
	}
	
	@PostMapping("/admin/foodproduct/add")
	public String productfoodAddPost(@ModelAttribute ("FoodDTO") FoodDTO foodDTO , 
								@RequestParam("productImage") MultipartFile file,
								@RequestParam("imgName") String imgName) throws IOException {
			
		Foodproduct foodpro = new Foodproduct();
		
		foodpro.setId(foodDTO.getId());
		foodpro.setName(foodDTO.getName());
		foodpro.setFood(foodservice.getFoodById(foodDTO.getFoodId()).get());
		foodpro.setPrice(foodDTO.getPrice());
		foodpro.setDescription(foodDTO.getDescription());
		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			
			imageUUID = imgName;
		}
		
		foodpro.setImageName(imageUUID);
		foodproservice.addfoodProduct(foodpro);
		
		
		return "redirect:/admin/foodproduct";
		
	}
	
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProd(@PathVariable int id) {
		
		productService.removeProductById(id);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProd(@PathVariable int id , Model model) {
		Product product = productService.getProductById(id).get();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("productDTO" , productDTO);
		model.addAttribute("categories" , categoryService.getAllCategories());
		
		return "productsAdd";
	}
	
	@GetMapping("/admin/foodproduct/delete/{id}")
	public String deleteProdfood(@PathVariable int id) {
		
		foodproservice.removefoodProductById(id);
		
		return "redirect:/admin/foodproduct";
	}
	
	@GetMapping("/admin/foodproduct/update/{id}")
	public String updateProdfood(@PathVariable int id , Model model) {
		Foodproduct product = foodproservice.getfoodProductById(id).get();
		
		FoodDTO productDTO = new FoodDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setFoodId(product.getFood().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("FoodDTO" , productDTO);
		model.addAttribute("food" , foodservice.getAllFood());
		
		return "foodproadd";
	}
	
	//Purchase Report
	@GetMapping("/admin/purchaseReport")
	public String purchaseReport(Model model) {
		
		List<Purchase>purchaseList = purchaseService.getAllPurchases();
		
		
		List<PurchaseReport>purchaseReportList = new ArrayList<PurchaseReport>();
		
		for(Purchase pur : purchaseList) {
			Product product = new Product();
			User user = new User();
			Category category = new Category();
			PurchaseReport purchaseReport =  new PurchaseReport();
			int productId = pur.getProductId();
			int userId = pur.getUserId();
			
			product = productService.getProductById(productId).get();
			user = userRepository.findById(userId).get();
			category = product.getCategory();
			
			purchaseReport.setEmail(user.getEmail());
			purchaseReport.setName(user.getFirstName());
			purchaseReport.setProductId(product.getId());
			purchaseReport.setProductName(product.getName());
			purchaseReport.setPrice(product.getPrice());
			purchaseReport.setDate(pur.getOrderDate().toString());
			purchaseReport.setCategory(category.getName());
			//System.out.println(purchaseReport.getEmail() + " " + purchaseReport.getProductId() + "  " + purchaseReport.getDate());
			
			purchaseReportList.add(purchaseReport);
			
		}
		
		model.addAttribute("purchaseList" , purchaseReportList);
		
		
		return "purchaseReport";
	}
	
	@GetMapping("/admin/users")
	public String listUsers(Model model) {
		
	
		List<User>userList = userRepository.findAll();
		model.addAttribute("userlist" , userList);
		
		return "userList";
	}
	
	@GetMapping("/admin/booking")
	public String listbooking(Model model) {
		
	
		List<bookingdetail>bookingList =bookingrepos.findAll();
		model.addAttribute("bookinglist" , bookingList);
		
		return "viewbooking";
	}
	
	@GetMapping("/admin/contact")
	public String listcontact(Model model) {
		
	
		List<contactus>contactList = contactrepos.findAll();
		model.addAttribute("contactlist" , contactList);
		
		return "viewcontact";
	}
	
	//Purchase Report
		@GetMapping("/admin/purchaseReportfood")
		public String purchaseReportfood(Model model) {
			
			List<purchaseFood>purchaseList = purfoodservice.getAllPurchases();
			
			
			List<PurchaseReportfood>purchaseReportList = new ArrayList<PurchaseReportfood>();
			
			for(purchaseFood pur : purchaseList) {
				Foodproduct product = new Foodproduct();
				User user = new User();
				Food category = new Food();
				PurchaseReportfood purchaseReport =  new PurchaseReportfood();
				int productId = pur.getProductId();
				int userId = pur.getUserId();
				
				product = foodproservice.getfoodProductById(productId).get();
				user = userRepository.findById(userId).get();
				category = product.getFood();
				
				purchaseReport.setEmail(user.getEmail());
				purchaseReport.setName(user.getFirstName());
				purchaseReport.setProductId(product.getId());
				purchaseReport.setProductName(product.getName());
				purchaseReport.setPrice(product.getPrice());
				purchaseReport.setDate(pur.getOrderDate().toString());
				purchaseReport.setCategory(category.getName());
				//System.out.println(purchaseReport.getEmail() + " " + purchaseReport.getProductId() + "  " + purchaseReport.getDate());
				
				purchaseReportList.add(purchaseReport);
				
			}
			
			model.addAttribute("purchaseList" , purchaseReportList);
			
			
			return "purchaseReportfood";
		}
		
		
}

