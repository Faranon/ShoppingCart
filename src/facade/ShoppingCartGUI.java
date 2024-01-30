package facade;
	
import buttons.AddProductButton;
import buttons.GUIButton;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class ShoppingCartGUI extends Application {
	private GUIButton addProductButton;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Shopping Cart");
		addProductButton = new AddProductButton("Add Product");
		
		Group root = new Group(addProductButton);
		Scene scene = new Scene(root, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}