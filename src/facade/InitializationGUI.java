package facade;
	
import buttons.AddProductButton;
import buttons.DeleteProductButton;
import buttons.EditProductButton;
import buttons.GUIButton;  
import buttons.LoadProductButton;
import collections.ProductList;
import entities.Product;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class InitializationGUI extends Application {
	private GUIButton addProductButton, loadProductButton, editProductButton,
		deleteProductButton;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Initialization");
		addProductButton = new AddProductButton("Add Product");
		loadProductButton = new LoadProductButton("Load Product");
		editProductButton = new EditProductButton("Edit Product");
		deleteProductButton = new DeleteProductButton("Delete Product");
		
		Button listProductButton = new Button("List Product");
		
		listProductButton.setOnAction(this::listProduct);
		
		//ListView<Product> listViewProducts = new ListView<>();
		
		VBox vButtonBox = new VBox(10);
		//HBox hBox = new HBox(100);
		//hBox.getChildren().addAll(listViewProducts);
		vButtonBox.getChildren().addAll(addProductButton, loadProductButton,
				editProductButton, deleteProductButton, listProductButton);
		
		Scene scene = new Scene(vButtonBox, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void listProduct(ActionEvent event) {
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}  
}