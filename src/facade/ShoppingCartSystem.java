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
	
	public static ShoppingCartSystem getInstance() {
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
	
	public void addProduct(String productName, String brand, String category, double price, int quantity) {
		Product newProduct = new Product(productName, brand, category, price, quantity);
		
		products.addProduct(newProduct);
		products.displayProducts();
	}
}
