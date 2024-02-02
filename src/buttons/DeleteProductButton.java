package buttons;

import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class DeleteProductButton extends GUIButton implements EventHandler<ActionEvent>{
	private ListView<String> listViewProducts;
	public DeleteProductButton(String buttonName, ListView<String> listViewProducts) {
		super(buttonName);
		
		this.listViewProducts = listViewProducts;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		String selectedProduct = listViewProducts.getSelectionModel().getSelectedItem();
		
		ShoppingCartSystem.instance().deleteProduct(selectedProduct);
		listViewProducts.getItems().remove(selectedProduct);
	}
}