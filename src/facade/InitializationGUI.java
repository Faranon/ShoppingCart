package facade;
	
import buttons.AddProductButton;
import buttons.DeleteProductButton;
import buttons.EditProductButton;
import buttons.GUIButton;
import buttons.LoadProductButton;
import buttons.StartButton;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class InitializationGUI extends Application {
	private GUIButton addProductButton, loadProductButton, editProductButton,
		deleteProductButton, startButton;
	
	private ListView<String> listViewProducts;
	private ObservableList<String> observableProducts;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Initialization");
		
		listViewProducts = new ListView<String>();
		observableProducts = FXCollections.observableArrayList();
		listViewProducts.setItems(observableProducts);
		
		// Buttons
		addProductButton = new AddProductButton("Add Product", observableProducts);
		loadProductButton = new LoadProductButton("Load Product File", observableProducts);
		editProductButton = new EditProductButton("Edit Product", listViewProducts);
		deleteProductButton = new DeleteProductButton("Delete Product", listViewProducts);
		startButton = new StartButton("Start", primaryStage);
		
		VBox vButtonBox = new VBox(10);
		HBox hListView = new HBox(5);
		HBox hAddAndLoadButton = new HBox(5);
		HBox hEditAndDeleteButton = new HBox(5);
		
		// adds all buttons to the vBox
		hAddAndLoadButton.getChildren().addAll(addProductButton, loadProductButton);
		hEditAndDeleteButton.getChildren().addAll(editProductButton, deleteProductButton);
		vButtonBox.getChildren().addAll(hAddAndLoadButton, hEditAndDeleteButton, 
				startButton);
		
		// adds the vButtonBox and listView to the hBox  
		hListView.getChildren().addAll(vButtonBox, listViewProducts);
		
		// disables edit and delete button unless a product is clicked in the listView
		editProductButton.disableProperty().bind(listViewProducts.getSelectionModel().selectedItemProperty().isNull());
		deleteProductButton.disableProperty().bind(listViewProducts.getSelectionModel().selectedItemProperty().isNull());
		
		listViewProducts.setPrefSize(790, 50);
		listViewProducts.setPlaceholder(new Label("Currently empty"));
		Scene scene = new Scene(hListView, 1000, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}  
}