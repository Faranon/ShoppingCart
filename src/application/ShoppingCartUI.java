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
		products.addProduct(newProduct2);
		
		products.displayProducts();
	}
}
