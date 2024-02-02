package buttons;

import java.io.File;

import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadProductButton extends GUIButton implements EventHandler<ActionEvent>{
	public LoadProductButton(String buttonName) {
		super(buttonName);
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		if(selectedFile != null) {
			ShoppingCartSystem.instance().readFile(selectedFile);
		}
	}
}