package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class ShoppingCartUI extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		
		Product newProduct = new Product("Soap", "fill", "fill", 1000);
		Product newProduct2 = new Product("Plate", "Something", "Nothing", 1);
		ProductList products = new ProductList();
		products.addProduct(newProduct);
		System.out.println(newProduct);
		
		newProduct.setProductName("Bar Soap");
		newProduct.setCategory("Soap");
		newProduct.setBrand("Dawn");
		newProduct.setQuantity(50);
		
		products.addProduct(newProduct2);
		
		System.out.println(newProduct);
		System.out.println(newProduct.getProductName());
		System.out.println(newProduct.getCategory());
		System.out.println(newProduct.getBrand());
		System.out.println(newProduct.getQuantity());
		
		products.displayProducts();
		products.removeProduct(newProduct2);
		products.removeProduct(newProduct);
		products.removeProduct(newProduct);
		products.displayProducts();
	}
}
