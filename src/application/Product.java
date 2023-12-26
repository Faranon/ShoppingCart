package application;

public class Product {
	String productName, category, brand;
	int quantity;
	
	public Product(String name, String brand, String category, int quantity) {
		this.productName = name;
		this.brand = brand;
		this.category = category;
		this.quantity = quantity;
	}
	
	
	// Setters
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
		return productName + " " + brand + " " + category + " " + quantity;
	}
}