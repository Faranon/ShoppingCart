package buttons;

import facade.ShoppingCartSystem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RemoveButton extends GUIButton implements EventHandler<ActionEvent>{
	private ListView<String> listViewShoppingCart;
	private ObservableList<String> observableShoppingCart;
	private Label totalL;
	
	public RemoveButton(String buttonName, ListView<String> listViewShoppingCart,
			ObservableList<String> observableShoppingCart, Label totalL) {
		super(buttonName);
		
		this.listViewShoppingCart = listViewShoppingCart;
		this.observableShoppingCart = observableShoppingCart;
		this.totalL = totalL;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		String selectedProduct = listViewShoppingCart.getSelectionModel().getSelectedItem();
		
		ShoppingCartSystem.instance().openRemoveQuantityGUI(selectedProduct, observableShoppingCart, totalL);
		
		//listViewShoppingCart.getItems().remove(selectedProduct);
	}
}