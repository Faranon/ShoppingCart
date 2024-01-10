package facade;
	
import buttons.AddProductButton;
import buttons.GUIButton;
//import collections.ProductList;
//import entities.Product;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class ShoppingCartGUI extends Application {
	private GUIButton addProductButton;
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Shopping Cart");
		addProductButton = new AddProductButton("Add Product");
		
		Group root = new Group(addProductButton);
		Scene scene = new Scene(root, 600, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		/*Button button1 = new Button();
		button1.setText("Add Product");
		button1.setTranslateX(150);
		button1.setTranslateY(60);
		
		Group root = new Group(button1);
		Scene scene = new Scene(root, 595, 150);
		primaryStage.setTitle("Shopping Cart");
		primaryStage.setScene(scene);
		primaryStage.show();*/
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
		/*Product newProduct = new Product("Soap", "fill", "fill", 1000);
		Product newProduct2 = new Product("Plate", "Something", "Nothing", 1);
		ProductList products = new ProductList();
		
		products.addProduct(newProduct);
		products.addProduct(newProduct2);
		
		products.displayProducts();*/
	}
}
