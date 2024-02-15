package buttons;

import java.io.File;

import facade.ShoppingCartSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadProductButton extends GUIButton implements EventHandler<ActionEvent>{
	private ListView<String> listViewProducts;
	
	public LoadProductButton(String buttonName, ListView<String> listViewProducts) {
		super(buttonName);
	
		this.listViewProducts = listViewProducts;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		if(selectedFile != null) {
			ShoppingCartSystem.instance().readFile(selectedFile);
			
			ObservableList<String> observableProducts = FXCollections.observableArrayList();
			ShoppingCartSystem.instance().sendObservableLP(observableProducts);
			
			listViewProducts.setItems(observableProducts);
		}
	}
}