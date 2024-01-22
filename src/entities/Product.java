package entities;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String productName, category, brand;
	private int quantity, productID;
	private double price;
	private static int counterID;
	
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
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice(double price) {
		this.price = price;
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
	
	public double getPrice() {
		return price;
	}
	
	// toString
	@Override
	public String toString() {
		return productID + " " + productName + " " + brand + " " + category + " " + quantity;
	}
}