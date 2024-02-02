/*
 * ProductList class is a singleton that stores all products created by the user into one
 * collection class.
 */
package collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.Product;

public class ProductList implements Iterable<Product>, Serializable{
	private static final long serialVersionUID = 1L;
	private List<Product> products = new ArrayList<Product>();
	private static ProductList productList;
	
	// Singleton
	private ProductList() {}
	
	// Makes sure that only one instance of the products list exists
	public static ProductList instance() {
		if(productList == null)
			productList = new ProductList();
		
		return productList;
	}
	
	// Adds the product to the products list
	public void addProduct(Product product) {
		products.add(product);
	}
	
	// Removes a product from the products list
	public Boolean removeProduct(Product product) {
		Iterator<Product> iterator = products.iterator();
		
		while(iterator.hasNext()) {
			Product currentProduct = iterator.next();
			if(currentProduct.equals(product)) {
				iterator.remove();
				return true;
			}
		}
		
		return false;
	}
	
	public Product searchProductByID(int productID) {
		for(Product product : products) {
			if(product.getProductID() == productID)
				return product;
		}
		
		return null;
	}
	
	// Displays all all contents of the products list
	public void displayProducts() {
		for(Product product : products) {
			System.out.println(product);
		}
	}

	@Override
	public Iterator<Product> iterator() {
		return products.iterator();
	}
}
