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
	public boolean checkProductName(String productName) {
		if(productName.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean checkProductBrand(String productBrand) {
		if(productBrand.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean checkProductCategory(String productCategory) {
		if(productCategory.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean checkProductPrice(String productPrice) {
		try {
			Double.parseDouble(productPrice);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean checkProductQuantity(String productQuantity) {
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
	
	/*
	 * This method reads a file that lists a bunch of products that the user wants to be
	 * added to the system. It uses a buffered reader to read the file and splits the
	 * line of string by ",". A boolean is then used to call a method to verify if each
	 * token is a valid input from the string. If it is, the product is added. Otherwise
	 * the system lets the user know which products were incorrect.
	 */
	public void readFile(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            
            // reads the file as long as it isn't null
            while ((line = reader.readLine()) != null) {
            	
            	// splits the line into tokens by ","
            	String[] tokens = line.split(",");
            	
            	// calls a method called validateFileInput to check each token
            	boolean isProductValid = validateFileInput(tokens);
            	
            	// if isProductValid is true, it can add the product
            	if(isProductValid) {
            		addProduct(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), 
            				tokens[3].trim(), tokens[4].trim());
            	} else {
            		
            		// lets the user know which products failed to be added
            		System.out.print("failed to add product: ");
            		
            		for(String token : tokens) {
            			System.out.print(token.trim() + " ");
            		}
            		
            		System.out.println();
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/*
	 * This method is used to validate input from a file. It takes a string of tokens and
	 * checks each one. If any token is found to be false, it'll return false right away.
	 */
	private boolean validateFileInput(String[] tokens) {
		boolean checkPName, checkPBrand, checkPCategory, checkPPrice, checkPQuantity;
		
		checkPName = checkProductName(tokens[0].trim());
		if(!checkPName) {
			return checkPName;
		}
		
		checkPBrand = checkProductBrand(tokens[1].trim());
		if(!checkPBrand) {
			return checkPBrand;
		}
		
		checkPCategory = checkProductCategory(tokens[2].trim());
		if(!checkPCategory) {
			return checkPCategory;
		}
		
		checkPPrice = checkProductPrice(tokens[3].trim());
		if(!checkPPrice) {
			return checkPPrice;
		}
		
		checkPQuantity = checkProductQuantity(tokens[4].trim());
		if(!checkPQuantity) {
			return checkPQuantity;
		}
		
		return true;
	}
	
	/*
	 * This method deletes a product given a string of the product. The method splits the
	 * string by spaces and takes the PID of the product and uses that to search for the
	 * product the user wants to delete. Once found, the product is removed from the
	 * ProductList products.
	 */
	public void deleteProduct(String selectedProduct) {
		String[] splitString = selectedProduct.split(" ");
		int getPID = Integer.parseInt(splitString[0]);
		
		Product productToDelete = products.searchProductByID(getPID);
		
		if(productToDelete != null) {
			products.removeProduct(productToDelete);
		}
	}
}