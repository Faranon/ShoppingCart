package buttons;

import java.io.File;

import facade.ShoppingCartSystem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadProductButton extends GUIButton implements EventHandler<ActionEvent>{
	private ObservableList<String> observableProducts;
	
	public LoadProductButton(String buttonName, ObservableList<String> observableProducts) {
		super(buttonName);
	
		this.observableProducts = observableProducts;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		if(selectedFile != null) {
			ShoppingCartSystem.instance().readFile(selectedFile, observableProducts);
			
			
		}
	}
}