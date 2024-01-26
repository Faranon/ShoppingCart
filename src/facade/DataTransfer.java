package facade;

import entities.Product;

public abstract class DataTransfer {
	private String productName, category, brand;
	private int quantity, productID;
	private double price;
	
	public DataTransfer() {
		//reset();
	}
	
	public void setProductFields(Product product) {
		productID = product.getProductID();
		category = product.getProductCategory();
		brand = product.getProductBrand();
		quantity = product.getProductQuantity();
		productID = product.getProductID();
		price = product.getProductPrice();
	}
	
	// getters and setters for product
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
	
	public int getProductID() {
		return productID;
	}
	
	public double getProductPrice() {
		return price;
	}
	
	public void setProductName(String newProductName) {
		this.productName = newProductName;
	}
	
	public void setProductCategory(String newCategory) {
		this.category = newCategory;
	}
	
	public void setProductBrand(String newBrand) {
		this.brand = newBrand;
	}
	
	public void setProductQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}
	
	public void setProductPrice(double newPrice) {
		this.price = newPrice;
	}
}
