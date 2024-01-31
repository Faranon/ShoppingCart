package buttons;

import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DeleteProductButton extends GUIButton implements EventHandler<ActionEvent>{
	public DeleteProductButton(String buttonName) {
		super(buttonName);
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		ShoppingCartSystem.instance().loadProduct();
	}
}