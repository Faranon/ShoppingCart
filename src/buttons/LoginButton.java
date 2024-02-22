package buttons;

import facade.ShoppingCartSystem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class LoginButton extends GUIButton implements EventHandler<ActionEvent> {
	private ObservableList<String> observableProducts;
	private Stage primaryStage;
	
	public LoginButton(String buttonName, ObservableList<String> observableProducts, Stage primaryStage) {
		super(buttonName);
		
		this.observableProducts = observableProducts;
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		ShoppingCartSystem.instance().openLoginGUI(observableProducts, primaryStage);
	}

}
