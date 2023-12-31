package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductList implements Iterable<Product>, Serializable{
	private static final long serialVersionUID = 1L;
	private List<Product> products;
	
	public ProductList() {
		this.products = new ArrayList<>();
	}
	
	public Boolean addProduct(Product product) {
		return products.add(product);
	}
	
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
