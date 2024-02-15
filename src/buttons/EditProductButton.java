package buttons;

import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class EditProductButton extends GUIButton implements EventHandler<ActionEvent>{
	private ListView<String> listViewProducts;
	
	public EditProductButton(String buttonName, ListView<String> listViewProducts) {
		super(buttonName);
		
		this.listViewProducts = listViewProducts;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		String selectedProduct = listViewProducts.getSelectionModel().getSelectedItem();
		ShoppingCartSystem.instance().openEditProductGUI(selectedProduct, listViewProducts);
	}
}