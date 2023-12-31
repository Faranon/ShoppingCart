package application;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String productName, category, brand;
	private int quantity, productID;
	private static int counterID;
	
	public Product(String name, String brand, String category, int quantity) {
		this.productName = name;
		this.brand = brand;
		this.category = category;
		this.quantity = quantity;
		
		productID = counterID++;
	}
	
	
	// Setters
	public void setProductID(int id) {
		this.productID = id;
	}
	
	public void setProductName(String name) {
		this.productName = name;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// Getters
	public int getProductID() {
		return productID;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	// toString
	@Override
	public String toString() {
		return productID + " " + productName + " " + brand + " " + category + " " + quantity;
	}
}