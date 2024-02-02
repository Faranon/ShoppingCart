package buttons;

import facade.ShoppingCartSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class ListProductButton extends GUIButton implements EventHandler<ActionEvent>{
	private ListView<String> listViewProducts;
	
	public ListProductButton(String buttonName, ListView<String> listViewProducts) {
		super(buttonName);
		
		this.listViewProducts = listViewProducts;
	}
	
	/*
	 * This method creates an observableList in order to create a dynamic, observable list.
	 * Then it calls a method in the ShoppingCartSystem called sendObservableLP to get the
	 * list of products that were created. The listView then displays the observableList
	 * and any changes to the observableList gets displayed in the listView.
	 */
	@Override
	public void handle(ActionEvent arg0) {
		ObservableList<String> observableProducts = FXCollections.observableArrayList();
		ShoppingCartSystem.instance().sendObservableLP(observableProducts);
		
		listViewProducts.setItems(observableProducts);
	}
}