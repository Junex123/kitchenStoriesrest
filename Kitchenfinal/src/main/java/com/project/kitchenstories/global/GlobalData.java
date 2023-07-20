package com.project.kitchenstories.global;

import java.util.ArrayList;
import java.util.List;

import com.project.kitchenstories.model.Foodproduct;
import com.project.kitchenstories.model.Product;

public class GlobalData {
	
	public static List<Product>cart;
	
	static {
		cart = new ArrayList<Product>();
	}
	
	public static List<Foodproduct>cart1;
	
	static {
		cart1 = new ArrayList<Foodproduct>();
	}
	
	

}