/*
 * Product.java is a class that is used to create products.
 */
package entities;

import java.io.Serializable;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Attributes of product
	private String productName, category, brand;
	private int quantity, productID;
	private double price;
	
	// Used to create unique ID's for each product that is created
	private static int counterID;
	
	// Constructor of product. It automatically generates the product ID
	public Product(String name, String brand, String category, double price, int quantity) {
		this.productName = name;
		this.brand = brand;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
		
		productID = counterID++;
	}
	
	
	// Setters
	public void setProductID(int id) {
		this.productID = id;
	}
	
	public void setProductName(String name) {
		this.productName = name;
	}
	
	public void setProductCategory(String category) {
		this.category = category;
	}
	
	public void setProductBrand(String brand) {
		this.brand = brand;
	}
	
	public void setProductQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setProductPrice(double price) {
		this.price = price;
	}
	
	// Getters
	public int getProductID() {
		return productID;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getProductCategory() {
		return category;
	}
	
	public String getProductBrand() {
		return brand;
	}
	
	public int getProductQuantity() {
		return quantity;
	}
	
	public double getProductPrice() {
		return price;
	}
	
	// toString
	@Override
	public String toString() {
		return productID + " | " + productName + " | " + brand + " | " + category + 
				" | " + price + " | " + quantity;
	}
}