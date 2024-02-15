package buttons;

import facade.ShoppingCartSystem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddProductButton extends GUIButton implements EventHandler<ActionEvent>{
	private ObservableList<String> observableProducts;
	
	public AddProductButton(String buttonName, ObservableList<String> observableProducts) {
		super(buttonName);
		
		this.observableProducts = observableProducts;
	}

	@Override
	public void handle(ActionEvent arg0) {
		ShoppingCartSystem.instance().openAddProductGUI(observableProducts);
	}
}