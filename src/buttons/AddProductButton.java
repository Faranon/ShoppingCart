package buttons;

import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddProductButton extends GUIButton implements EventHandler<ActionEvent>{
	public AddProductButton(String buttonName) {
		super(buttonName);
	}

	@Override
	public void handle(ActionEvent arg0) {
		ShoppingCartSystem.instance().openAddProductGUI();
	}
}