/*
 * Author: Ethan Lo
 * ShoppingCartSystem does all the backend for the ShoppingCartGUI. The ShoppingCartSystem
 * is a singleton, meaning that there can only be one ShoppingCartSystem that is created.
 */
package facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import collections.ProductList;
import entities.Product;
import javafx.collections.ObservableList;

public class ShoppingCartSystem implements Serializable{
	private static final long serialVersionUID = 1L;
	private ProductList products = ProductList.instance();
	
	private static ShoppingCartSystem system;
	
	// Singleton
	private ShoppingCartSystem() {
		system = this;
	}
	
	/*
	 *  Checks if the ShoppingCartSystem has already been initialized. If it hasn't,
	 *  create a new system, otherwise return itself. This is to ensure that
	 *  another instance of the ShoppingCartSystem isn't created. Whenever a method is
	 *  called outside of this class, they have to call this method first.
	 */
	public static ShoppingCartSystem instance() {
		if(system == null) {
			system = new ShoppingCartSystem();
		}
		
		return system;
	}
	
	/*
	 * This method gets called from the AddProductButton.java class to create the
	 * AddProductGUI.java GUI when addProduct button is clicked.
	 */
	public void openAddProductGUI() {
		AddProductGUI addProductGUI = new AddProductGUI();
	}
	
	/*
	 * The checkProduct methods are used to check for valid user input from the text
	 * fields in the AddProductGUI in order to properly create a product.
	 */
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
	
	/*
	 * This method gets called in the AddProductGUI.java once all boolean checks pass true.
	 * The productPrice and productQuantity both get casted into a double and int. The
	 * product is then created and added to the products list.
	 */
	public void addProduct(String productName, String productBrand, String productCategory,
			String productPrice, String productQuantity) {
		
		// converts string productPrice and productQuantity to a double and int
		double convertedProductPrice = Double.parseDouble(productPrice);
		int convertedProductQuantity = Integer.parseInt(productQuantity);
		
		Product newProduct = new Product(productName, productBrand, productCategory,
				convertedProductPrice, convertedProductQuantity);
		
		products.addProduct(newProduct);
	}
	
	/*
	 * This method is used to copy products from the ProductList to the observableList.
	 * It then returns the observableList.
	 */
	public ObservableList<String> sendObservableLP (ObservableList<String> observableLP) {
		for(Product product : products) {
			observableLP.add(product.toString());
		}
		
		return observableLP;
	}
	
	public void readFile(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line (assuming it contains product information)
                System.out.println("Read line: " + line);
                // Parse the line and create Product objects if needed
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}