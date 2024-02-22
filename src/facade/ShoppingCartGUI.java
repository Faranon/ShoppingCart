package facade;

import buttons.AddButton;
import buttons.CheckoutButton;
import buttons.GUIButton;
import buttons.LoginButton;
import buttons.RemoveButton;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ShoppingCartGUI {
	private Stage primaryStage;
	
	private TextField searchBarTF = new TextField();
	private Label totalL = new Label("Total: $0.00");
	
	private GUIButton searchButton, addButton, removeButton, checkoutButton, loginButton;
	private ComboBox<String> comboBox;
	
	private ListView<String> listViewProducts, listViewCart;
	private ObservableList<String> observableProducts, observableCart;
	
	public ShoppingCartGUI(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Shopping Cart");
		
		searchBarTF.setPrefWidth(285);
		
		listViewProducts = new ListView<String>();
		listViewProducts.setPrefSize(250, 500);
		
		listViewCart = new ListView<String>();
		listViewCart.setPrefSize(250, 400);
		
		observableCart = FXCollections.observableArrayList();
		listViewCart.setItems(observableCart);
		
		// method used to get products list
		getObservableProducts();
        
		// comboBox created
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Product Name", "Brand", "Category");
		
        // buttons
        searchButton = new SearchButton("Search", searchBarTF, comboBox, listViewProducts);
        addButton = new AddButton("Add", listViewProducts, observableCart, totalL);
        removeButton = new RemoveButton("Remove", listViewCart, observableCart, totalL);
        checkoutButton = new CheckoutButton("Check Out", observableProducts, observableCart, totalL);
        loginButton = new LoginButton("Login", observableProducts, primaryStage);
        
        // disable buttons
        addButton.disableProperty().bind(listViewProducts.getSelectionModel().selectedItemProperty().isNull());
        removeButton.disableProperty().bind(listViewCart.getSelectionModel().selectedItemProperty().isNull());
        checkoutButton.disableProperty().bind(listViewCart.getSelectionModel().selectedItemProperty().isNull());
        
        // ShoppingCartGUI format
        SplitPane splitPane = new SplitPane();
        
        // left side of split pane
        HBox hBoxL = new HBox();
        VBox vBoxL = new VBox();
        hBoxL.getChildren().addAll(searchBarTF, comboBox, searchButton, addButton);
        vBoxL.getChildren().addAll(hBoxL, listViewProducts);
        
        // right side of split pane
        HBox upHBoxR = new HBox();
        HBox lowHBoxR = new HBox();
        VBox vBoxR = new VBox();
        
        Label cartL = new Label("Cart");
        cartL.setFont(new Font("Arial", 22));
        upHBoxR.getChildren().addAll(cartL);
        upHBoxR.setAlignment(Pos.CENTER);
        
        lowHBoxR.getChildren().addAll(removeButton, checkoutButton, loginButton);
        vBoxR.getChildren().addAll(upHBoxR, listViewCart, totalL, lowHBoxR);
        
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