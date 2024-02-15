package buttons;

import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class AddProductButton extends GUIButton implements EventHandler<ActionEvent>{
	private ListView<String> listViewProducts;
	
	public AddProductButton(String buttonName, ListView<String> listViewProducts) {
		super(buttonName);
		
		this.listViewProducts = listViewProducts;
	}

	@Override
	public void handle(ActionEvent arg0) {
		ShoppingCartSystem.instance().openAddProductGUI(listViewProducts);
	}
}