package buttons;

import facade.ShoppingCartSystem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AddButton extends GUIButton implements EventHandler<ActionEvent>{
	private ListView<String> listViewProducts;
	private ObservableList<String> observableShoppingCart;
	private Label totalL;
	
	public AddButton(String buttonName, ListView<String> listViewProducts,
			ObservableList<String> observableShoppingCart, Label totalL) {
		super(buttonName);
		
		this.listViewProducts = listViewProducts;
		this.observableShoppingCart = observableShoppingCart;
		this.totalL = totalL;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		String selectedProduct = listViewProducts.getSelectionModel().getSelectedItem();
		
		ShoppingCartSystem.instance().openAddQuantityGUI(selectedProduct, observableShoppingCart, totalL);
	}
}