package facade;

import buttons.AddButton;
import buttons.GUIButton;
import buttons.SearchButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ShoppingCartGUI {
	private Stage primaryStage;
	private TextField searchBarTF = new TextField();
	private GUIButton searchButton, addButton, deleteButton, checkoutButton;
	private ComboBox<String> comboBox;
	
	private ListView<String> listViewProducts, listViewShoppingCart;
	private ObservableList<String> observableProducts, observableShoppingCart;
	
	ShoppingCartGUI(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Shopping Cart");
		
		searchBarTF.setPrefWidth(285);
		
		listViewProducts = new ListView<String>();
		listViewProducts.setPrefSize(250, 950);
		
		listViewShoppingCart = new ListView<String>();
		listViewShoppingCart.setPrefSize(250, 400);
		
		observableShoppingCart = FXCollections.observableArrayList();
		listViewShoppingCart.setItems(observableShoppingCart);
		
		// method used to get products list
		getObservableProducts();
        
		// comboBox created
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Product Name", "Brand", "Category");
		
        // buttons
        searchButton = new SearchButton("Search", searchBarTF, comboBox, listViewProducts);
        addButton = new AddButton("Add", listViewProducts, observableShoppingCart);
        
        // disable buttons
        addButton.disableProperty().bind(listViewProducts.getSelectionModel().selectedItemProperty().isNull());
        
        // ShoppingCartGUI format
        SplitPane splitPane = new SplitPane();
        
        // left side of split pane
        HBox hBoxL = new HBox();
        VBox vBoxL = new VBox();
        hBoxL.getChildren().addAll(searchBarTF, comboBox, searchButton, addButton);
        vBoxL.getChildren().addAll(hBoxL, listViewProducts);
        
        // right side of split pane
        HBox hBoxR = new HBox();
        VBox vBoxR = new VBox();
        StackPane stackPaneR = new StackPane();
        
        Label cartL = new Label("Cart");
        cartL.setAlignment(Pos.CENTER);
        cartL.setFont(new Font("Arial", 22));
        stackPaneR.getChildren().addAll(cartL);
        
        hBoxR.getChildren().addAll();
        vBoxR.getChildren().addAll(stackPaneR, listViewShoppingCart, hBoxR);
        
        splitPane.getItems().addAll(vBoxL, vBoxR);
        
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