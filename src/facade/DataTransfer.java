package facade;

import entities.Product;

public abstract class DataTransfer {
	private String productName, category, brand;
	private int quantity, productID;
	
	public DataTransfer() {
		//reset();
	}
	
	public void setProductFields(Product product) {
		productID = product.getProductID();
		category = product.getCategory();
		brand = product.getBrand();
		quantity = product.getQuantity();
		productID = product.getProductID();
	}
	
	// getters and setters for product
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
	
	public int getProductID() {
		return productID;
	}
	
	public void setProductName(String newProductName) {
		this.productName = newProductName;
	}
	
	public void setCategory(String newCategory) {
		this.category = newCategory;
	}
	
	public void setBrand(String newBrand) {
		this.brand = newBrand;
	}
	
	public void setProductName(int newQuantity) {
		this.quantity = newQuantity;
	}
}
