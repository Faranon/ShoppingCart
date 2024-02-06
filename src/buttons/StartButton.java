package buttons;

import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class StartButton extends GUIButton implements EventHandler<ActionEvent>{
	private Stage primaryStage;
	
	public StartButton(String buttonName, Stage primaryStage) {
		super(buttonName);
		
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		ShoppingCartSystem.instance().startShoppingCartGUI(primaryStage);
	}
}