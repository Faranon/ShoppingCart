package buttons;

import facade.ShoppingCartSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SearchButton extends GUIButton implements EventHandler<ActionEvent>{
	private String textField, option;
	private TextField searchBarTF;
	private ComboBox<String> comboBox;
	private ListView<String> listViewProducts;
	
	public SearchButton(String buttonName, TextField searchBarTF, ComboBox<String> comboBox, 
			ListView<String> listViewProducts) {
		super(buttonName);
		
		this.searchBarTF = searchBarTF;
		this.comboBox = comboBox;
		this.listViewProducts = listViewProducts;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		option = comboBox.getSelectionModel().getSelectedItem();
		textField = searchBarTF.getText();
		
		ObservableList<String> searchedOP;
		searchedOP = FXCollections.observableArrayList();
		
		if(option == null) {
			comboBox.setPromptText("Pick an option");
		} else if(option.equals("Product Name")) {
			searchProductByName(searchedOP);
		} else if(option.equals("Brand")) {
			searchProductByBrand(searchedOP);
		} else if(option.equals("Category")) {
			searchProductByCategory(searchedOP);
		}
	}
	
	/*
	 * These search methods calls a method inside the ShoppingCartSystem depending on which option the
	 * comboBox is picked. Each method clears the listView. Then they check if the searchedOP is empty.
	 * If it is, it tells the user that no such product is available. Otherwise it displays all items that
	 * contain the string in the searchBar.
	 */
	private void searchProductByName(ObservableList<String> searchedOP) {
		ShoppingCartSystem.instance().searchProductByName(textField, searchedOP);
		
		listViewProducts.getItems().clear();
		
		if(searchedOP.isEmpty()) {
			listViewProducts.setPlaceholder(new Label("No products available"));
		} else {
			listViewProducts.setItems(searchedOP);
		}
	}
	
	private void searchProductByBrand(ObservableList<String> searchedOP) {
		ShoppingCartSystem.instance().searchProductByBrand(textField, searchedOP);
		
		listViewProducts.getItems().clear();
		
		if(searchedOP.isEmpty()) {
			listViewProducts.setPlaceholder(new Label("No products available"));
		} else {
			listViewProducts.setItems(searchedOP);
		}
	}
	
	private void searchProductByCategory(ObservableList<String> searchedOP) {
		ShoppingCartSystem.instance().searchProductByCategory(textField, searchedOP);
		
		listViewProducts.getItems().clear();
		
		if(searchedOP.isEmpty()) {
			listViewProducts.setPlaceholder(new Label("No products available"));
		} else {
			listViewProducts.setItems(searchedOP);
		}
	}
}