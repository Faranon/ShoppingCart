package facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;

import collections.ProductList;

public class ShoppingCartSystem implements Serializable{
	private static final long serialVersionUID = 1L;
	private ProductList products = ProductList.getInstance();
	private ShoppingCartSystem system;
	private BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	private ShoppingCartSystem() {}
	
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
	
	public void addProduct() {
		
	}
}
