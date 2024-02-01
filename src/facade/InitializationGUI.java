package facade;
	
import buttons.AddProductButton;
import buttons.DeleteProductButton;
import buttons.EditProductButton;
import buttons.GUIButton;
import buttons.LoadProductButton;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class InitializationGUI extends Application {
	private GUIButton addProductButton, loadFileButton, editProductButton,
		deleteProductButton;
	
	private ListView<String> listViewProducts;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Initialization");
		addProductButton = new AddProductButton("Add Product");
		loadFileButton = new LoadProductButton("Load File");
		editProductButton = new EditProductButton("Edit Product");
		deleteProductButton = new DeleteProductButton("Delete Product");
		
		Button listProductButton = new Button("List Product");
		listProductButton.setOnAction(this::listProduct);
		
		listViewProducts = new ListView<>();
		
		VBox vButtonBox = new VBox(10);
		HBox hListView = new HBox(100);
		
		// adds all buttons to the vBox
		vButtonBox.getChildren().addAll(addProductButton, loadFileButton,
				editProductButton, deleteProductButton, listProductButton);
		
		// adds the vButtonBox and listView to the hBox  
		hListView.getChildren().addAll(vButtonBox, listViewProducts);
		
		Scene scene = new Scene(hListView, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/*
	 * This method creates an observableList in order to create a dynamic, observable list.
	 * Then it calls a method in the ShoppingCartSystem called sendObservableLP to get the
	 * list of products that were created. The listView then displays the observableList
	 * and any changes to the observableList gets displayed in the listView.
	 */
	private void listProduct(ActionEvent event) {
		ObservableList<String> observableProducts = FXCollections.observableArrayList();
		ShoppingCartSystem.instance().sendObservableLP(observableProducts);
		
		listViewProducts.setItems(observableProducts);
	}
	
	public static void main(String[] args) {
		launch(args);

	}  
}