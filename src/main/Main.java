package main;

import java.io.File;

import facade.ShoppingCartGUI;
import facade.ShoppingCartSystem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ObservableList<String> observableShoppingCart = FXCollections.observableArrayList();
		
		File selectedFile = new File("productOrder.txt");
		
		if(selectedFile != null) {
			ShoppingCartSystem.instance().readFile(selectedFile, observableShoppingCart);
			ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI(primaryStage);
			shoppingCartGUI.showShoppingCartGUI();
		}
	}
}