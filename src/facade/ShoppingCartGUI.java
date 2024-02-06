package facade;

import entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ShoppingCartGUI {
	private Stage ShoppingCartStage;
	private ListView<Product> listView;
	private ObservableList<Product> productOL;
	
	ShoppingCartGUI(Stage primaryStage) {
		ShoppingCartStage = primaryStage;
	}
	
	public void startShoppingCartGUI() {
		ShoppingCartStage = new Stage();
		listView = new ListView<>();
		productOL = FXCollections.observableArrayList();
		
        listView.getItems().addAll();

        ShoppingCartStage.setScene(new Scene(listView, 400, 300));
        ShoppingCartStage.setTitle("Shopping Cart");
        ShoppingCartStage.show();
	}
}
