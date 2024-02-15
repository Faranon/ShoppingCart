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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
	public void openAddProductGUI(ObservableList<String> observableProducts) {
		AddProductGUI addProductGUI = new AddProductGUI(observableProducts);
		addProductGUI.showAddProductGUI();
	}
	
	/*
	 * This method opens up the editProductGUI and fills in the product to be edited in the text fields.
	 * It starts by searching for the product that is to be edited by its ID. Then creates a bunch of string
	 * variables for each attribute of product except the product ID. Then it sends it to the EditProductGUI
	 * and fills in the correct text fields.
	 */
	public void openEditProductGUI(String selectedProduct, ListView<String> listViewProducts) {
		// finds the selected product using the product ID
		String[] splitString = selectedProduct.split(" \\| ");
		int getPID = Integer.parseInt(splitString[0]);
		
		Product productToBeEdit = searchProductById(getPID);
		
		String pName, pBrand, pCategory, pPrice, pQuantity;
		
		int pID = productToBeEdit.getProductID();
		pName = productToBeEdit.getProductName();
		pBrand = productToBeEdit.getProductBrand();
		pCategory = productToBeEdit.getProductCategory();
		pPrice = Double.toString(productToBeEdit.getProductPrice());
		pQuantity = Integer.toString(productToBeEdit.getProductQuantity());
		
		EditProductGUI editProductGUI = new EditProductGUI(pID, pName, pBrand, pCategory, pPrice,
				pQuantity, listViewProducts);
		editProductGUI.showEditProductGUI();
	}
	
	public void openAddQuantityGUI(String selectedProduct, ObservableList<String> observableShoppingCart,
			Label totatL) {
		AddQuantityGUI addQuantityGUI = new AddQuantityGUI(selectedProduct, observableShoppingCart, totatL);
		addQuantityGUI.showAddProductGUI();
	}
	
	/*
	 * This method starts up the shopping cart GUI and uses the products created to mimic an actual
	 * shopping cart system.
	 */
	public void startShoppingCartGUI(Stage primaryStage) {
		ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI(primaryStage);
		shoppingCartGUI.showShoppingCartGUI();
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
			if(Double.parseDouble(productPrice) <= 0) {
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean checkProductQuantity(String productQuantity) {
		try {
			if (Integer.parseInt(productQuantity) <= 0) {
				return false;
			}
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
	public void addProduct(String pName, String pBrand, String pCategory, String pPrice,
			String pQuantity, ObservableList<String> observableProducts) {
		
		// converts string productPrice and productQuantity to a double and int
		double convertedPPrice = Double.parseDouble(pPrice);
		int convertedPQuantity = Integer.parseInt(pQuantity);
		
		Product newProduct = new Product(pName, pBrand, pCategory,
				convertedPPrice, convertedPQuantity);
		
		products.addProduct(newProduct);
		observableProducts.add(newProduct.toString());
	}
	
	public void editProduct(int pID, String pName, String pBrand, String pCategory,
			String pPrice, String pQuantity) {
		Product productToEdit = searchProductById(pID);
		
		// converts string productPrice and productQuantity to a double and int
		double convertedPPrice = Double.parseDouble(pPrice);
		int convertedPQuantity = Integer.parseInt(pQuantity);
		
		productToEdit.setProductName(pName);
		productToEdit.setProductBrand(pBrand);
		productToEdit.setProductCategory(pCategory);
		productToEdit.setProductPrice(convertedPPrice);
		productToEdit.setProductQuantity(convertedPQuantity);
	}
	
	/*
	 * This method searches products by their IDs using the searchProductByID method. If the product exits
	 * it returns the product. If not, it returns null.
	 */
	private Product searchProductById(int getPID) {
		for(Product product : products) {
			if(product.getProductID() == getPID)
				return product;
		}
		
		return null;
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
		
		Product productToDelete = searchProductById(getPID);
		
		if(productToDelete != null) {
			products.removeProduct(productToDelete);
		}
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
	public void readFile(File file, ObservableList<String> observableProducts) {
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
            				tokens[3].trim(), tokens[4].trim(), observableProducts);
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
	 * These methods searches for a product depending on which comboBox the user selects. It uses the
	 * ObservableList searchedOP to add whatever products contains the string. Note that these methods
	 * ignore capitalization by converting both the get...() method and string to lower case letters.
	 * The user does not need to spell the searched product name exactly. The methods return an observable
	 * list.
	 */
	public ObservableList<String> searchProductByName(String pName, ObservableList<String> searchedOP) {
		
		for(Product product : products) {
			if(product.getProductName().toLowerCase().contains(pName.toLowerCase()))
				searchedOP.add(product.toString());
		}
		
		return searchedOP;
	}
	
	public ObservableList<String> searchProductByBrand(String pBrand, ObservableList<String> searchedOP) {
			
			for(Product product : products) {
				if(product.getProductBrand().toLowerCase().contains(pBrand.toLowerCase()))
					searchedOP.add(product.toString());
		}
			
			return searchedOP;
	}
	
	public ObservableList<String> searchProductByCategory(String pCategory,
			ObservableList<String> searchedOP) {
		
		for(Product product : products) {
			if(product.getProductCategory().toLowerCase().contains(pCategory.toLowerCase()))
				searchedOP.add(product.toString());
		}
		
		return searchedOP;
	}
	
	/*
	 * This method takes the productCap, which is the total quantity of a product, and takes the
	 * userQInput, which contains the quantity that the user wants. The if statement checks if the
	 * userQInput is a valid positive integer that is greater than 1. It also checks to see if the 
	 * userQInput is less than or equal to the productCap. If the userQInput is not a valid integer, then
	 * it is caught in the catch block returning false. The same is said to be done if the userQInput is
	 * greater than the productCap.
	 */
	public boolean validateQuantity(int productCap, String userQInput) {
		try {
			if(Integer.parseInt(userQInput) >= 1 &&
					Integer.parseInt(userQInput) <= productCap) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/*
	 * This method takes several variables in order to add a product to the user's cart. The splitProduct
	 * is an array of a string that contains information of the product. The observableList is a string 
	 * containing the list of products already in the user's cart. The quantityInput is the quantity the
	 * user wants of the product and the productCap is the total quantity that is available for sell. 
	 * 
	 * The if statement checks if the cart is empty. If it is, add the product and quantity to the cart.
	 * The string[] splitProduct[4] is the position of the price per product. It gets multiplied by the
	 * quantity. The string[] splitProduct[5] is the position of the quantity in the string and gets
	 * changed depending on what the quantityInput the user wanted, returns true.
	 * 
	 * The if else statement calls a method called checkProductInCartAndCapped which is a boolean method.
	 * If it is true, it returns true. Otherwise, the method returns false.
	 */
	public boolean addToCart(String[] splitProduct, ObservableList<String> observableShoppingCart,
			int quantityInput, int productCap) {
		
		double total = 0;
		
		// if cart is empty, add it to the cart
		if(observableShoppingCart.isEmpty()) {
			total = getProductTotalPrice(total, splitProduct, quantityInput);
			
			String combinedProduct = stringConversionsAddingToCart(total, splitProduct, quantityInput);
			
			observableShoppingCart.add(combinedProduct);
			return true;
		} else if(checkProductInCartAndCapped(splitProduct, observableShoppingCart,
				quantityInput, productCap, total)) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * This method is a private method that gets called in the addToCart() method. It is used to check if
	 * the product already exists in the cart and to see if the total quantity in the cart exceeds the
	 * maximum quantity available. 
	 */
	private boolean checkProductInCartAndCapped(String[] splitProduct,
			ObservableList<String> observableShoppingCart, int quantityInput, int productCap, double total) {
		
		// used to track the index position inside the observableShoppingCart
		int index = 0;
		
		// iterates through the cart, splitting the product information into tokens
		for(String product : observableShoppingCart) {
			String[] searchedP = product.split(" \\| ");
			
			// searchedP[0] and splitProduct[0] are both pIDs that are compared with each other
			if(searchedP[0].equals(splitProduct[0])) {
				// if the product is already in the cart, add the quantity together
				quantityInput = quantityInput + Integer.parseInt(searchedP[5]);
				
				// check if the quantityInput exceeds the productCap
				if(quantityInput <= productCap) {
					total = getProductTotalPrice(total, splitProduct, quantityInput);
					
					String combinedProduct = stringConversionsAddingToCart(total, splitProduct, quantityInput);
					
					observableShoppingCart.set(index, combinedProduct);
					return true;
				} else
					return false;
			}
			
			index++;
		}
		
		// if the product is not in the cart, check if the quantityInput is greater than the productCap
		if(quantityInput <= productCap) {
			total = getProductTotalPrice(total, splitProduct, quantityInput);
			
			String combinedProduct = stringConversionsAddingToCart(total, splitProduct, quantityInput);
			
			observableShoppingCart.add(combinedProduct);
			return true;
		}
		
		return false;
	}
	
	/*
	 * This is a private method that gets called in the addToCart() and checkProductInCartAndCapped(). It 
	 * is used to get the total price of a product by multiplying it with its quantity. Returns the total.
	 */
	private double getProductTotalPrice(double total, String[] splitProduct, int quantityInput) {
		
		// price of the product * by the quantity
		total = Double.parseDouble(splitProduct[4].substring(1)) * quantityInput;
		
		return total;
	}
	
	/*
	 * This is a private method that gets called in the addToCart() and checkProductInCartAndCapped() to
	 * convert the total and quantity into a string. It also combines the splitProduct string into one and
	 * returns it.
	 */
	private String stringConversionsAddingToCart(double total, String[] splitProduct, int quantityInput) {
		splitProduct[4] = String.format("$%.2f", total);
		
		// converts the quantityInput into a string and then the splitProduct is combined
		splitProduct[5] = String.valueOf(quantityInput);
		String joinString = String.join(" | ", splitProduct);
		
		return joinString;
	}
	
	/*
	 * This method gets the total cost of all products in the cart. It takes the observableShoppingCart
	 * which contains all the products currently in the cart and adds up the total of all products.
	 */
	public String getCartTotalPrice(ObservableList<String> observableShoppingCart) {
		double total = 0;
		
		// iterates through the observableShoppingCart, the string product gets all the product info
		for(String product : observableShoppingCart) {
			
			// split the product info into tokens
			String[] splitP = product.split(" \\| ");
			
			// splitP[0] contains the pID
			int pID = Integer.parseInt(splitP[0]);
			
			// find the product associated with the pID and get the price of the product
			Product searchProduct = searchProductById(pID);
			Double priceOfProduct = searchProduct.getProductPrice();
			
			total = total + priceOfProduct * Double.parseDouble(splitP[5]);
		}
		
		String totalCartPrice = String.format("$%.2f", total);
		
		return totalCartPrice;
	}
}