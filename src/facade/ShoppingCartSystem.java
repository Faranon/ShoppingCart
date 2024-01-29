package facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import collections.ProductList;
import entities.Product;

public class ShoppingCartSystem implements Serializable{
	private static final long serialVersionUID = 1L;
	private ProductList products = ProductList.getInstance();
	private BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	private static ShoppingCartSystem system;
	
	private ShoppingCartSystem() {
		system = this;
	}
	
	public static ShoppingCartSystem instance() {
		if(system == null) {
			system = new ShoppingCartSystem();
		}
		
		return system;
	}
	
	public String getInput(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				return line;
			} catch(IOException ioe) {
				System.exit(0);
			}
		} while(true);
	}
	
	public void openAddProductGUI() {
		AddProductGUI addProductGUI = new AddProductGUI();
	}
	
	public boolean checkProductNameField(String productName) {
		if(productName.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean checkProductBrandField(String productBrand) {
		if(productBrand.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean checkProductCategoryField(String productCategory) {
		if(productCategory.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean checkProductPriceField(String productPrice) {
		try {
			Double.parseDouble(productPrice);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean checkProductQuantityField(String productQuantity) {
		try {
			Integer.parseInt(productQuantity);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public void addProduct(String productName, String productBrand, String productCategory,
			String productPrice, String productQuantity) {
		
		double convertedProductPrice = Double.parseDouble(productPrice);
		int convertedProductQuantity = Integer.parseInt(productQuantity);
		
		Product newProduct = new Product(productName, productBrand, productCategory,
				convertedProductPrice, convertedProductQuantity);
		
		products.addProduct(newProduct);
		products.displayProducts();
	}
}
