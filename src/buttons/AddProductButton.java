package buttons;

import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddProductButton extends GUIButton implements EventHandler<ActionEvent>{
	private TextField productNameTF = new TextField();
	private TextField productCategoryTF = new TextField();
	private TextField productBrandTF = new TextField();
	private TextField productPriceTF = new TextField();
	private TextField productQuantityTF = new TextField();
	
	private Stage addProductStage = new Stage();
	
	public AddProductButton(String buttonName) {
		super(buttonName);
	}

	@Override
	public void handle(ActionEvent arg0) {
		Label productName = new Label("Product Name:");
		Label productCategory = new Label("Product Category:");
		Label productBrand= new Label("Product Brand:");
		Label productPrice = new Label("Product Price:");
		Label productQuantity = new Label("Product Quantity:");
		
		Button cancelButton = new Button("Cancel");
		Button confirmButton = new Button("Confirm");
		
		confirmButton.setOnAction(this::confirmButtonClicked);
		cancelButton.setOnAction(this::cancelButtonClicked);
		
		VBox vbox = new VBox(10);
		HBox hbox = new HBox(100);
		
		hbox.getChildren().addAll(cancelButton, confirmButton);
		
		vbox.getChildren().addAll(productName, productNameTF, productCategory, productCategoryTF,
				productBrand, productBrandTF, productPrice, productPriceTF, productQuantity,
				productQuantityTF, hbox);
		
		Scene scene = new Scene(vbox, 300, 350);
		addProductStage.setScene(scene);
		addProductStage.show();
	}
	
	private void confirmButtonClicked(ActionEvent event) {
		String getProductName = productNameTF.getText();
		String getProductBrand = productBrandTF.getText();
		String getProductCategory= productCategoryTF.getText();
		String getProductPrice = productPriceTF.getText();
		String getProductQuantity = productQuantityTF.getText();
		
		double price = Double.valueOf(getProductPrice);
		int quantity = Integer.parseInt(getProductQuantity);
		
		ShoppingCartSystem.getInstance().addProduct(getProductName, getProductBrand, getProductCategory, price, quantity);
		
		addProductStage.close();
	}
	
	private void cancelButtonClicked(ActionEvent event) {
		addProductStage.close();
	}
}
