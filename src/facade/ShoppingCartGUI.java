package facade;

import buttons.GUIButton;
import buttons.SearchButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShoppingCartGUI {
	private Stage primaryStage;
	private TextField searchBarTF = new TextField();
	private GUIButton searchButton;
	private ComboBox<String> comboBox;
	
	private ListView<String> listViewProducts;
	private ObservableList<String> observableProducts;
	
	ShoppingCartGUI(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Shopping Cart");
		
		searchBarTF.setPrefWidth(280);
		
		listViewProducts = new ListView<String>();
		listViewProducts.setPrefSize(250, 950);
		
		// method used to get products list
		getObservableProducts();
        
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Product Name", "Brand", "Category");
		
        searchButton = new SearchButton("Search Button", searchBarTF, comboBox, listViewProducts);
        
        StackPane leftStackPane = new StackPane();
        StackPane rightStackPane = new StackPane();
        SplitPane splitPane = new SplitPane();
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        
        hBox.getChildren().addAll(searchBarTF, comboBox, searchButton);
        vBox.getChildren().addAll(hBox, listViewProducts);
        leftStackPane.getChildren().add(vBox);
        splitPane.getItems().addAll(leftStackPane, rightStackPane);
        
        primaryStage.setScene(new Scene(splitPane, 1000, 500));
	}
	
	public void showShoppingCartGUI() {
		primaryStage.show();
	}
	
	private void getObservableProducts() {
		observableProducts = FXCollections.observableArrayList();
		ShoppingCartSystem.instance().sendObservableLP(observableProducts);
		listViewProducts.setItems(observableProducts);
	}
}