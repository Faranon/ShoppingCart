package facade;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddProductGUI {
	private TextField productNameTF = new TextField();
	private TextField productBrandTF = new TextField();
	private TextField productCategoryTF = new TextField();
	private TextField productPriceTF = new TextField();
	private TextField productQuantityTF = new TextField();
	
	private Label productNameL = new Label("Product Name:");
	private Label productBrandL = new Label("Product Brand:");
	private Label productCategoryL = new Label("Product Category:");
	private Label productPriceL = new Label("Product Price:");
	private Label productQuantityL = new Label("Product Quantity:");
	
	private Stage addProductStage = new Stage();
	
	public AddProductGUI() {
		Button cancelButton = new Button("Cancel");
		Button confirmButton = new Button("Confirm");
		
		confirmButton.setOnAction(this::confirmButtonClicked);
		cancelButton.setOnAction(this::cancelButtonClicked);
		
		VBox vbox = new VBox(10);
		HBox hbox = new HBox(100);
		
		hbox.getChildren().addAll(cancelButton, confirmButton);
		
		vbox.getChildren().addAll(productNameL, productNameTF, productBrandL, productBrandTF,
				productCategoryL, productCategoryTF, productPriceL, productPriceTF, productQuantityL,
				productQuantityTF, hbox);
		
		Scene scene = new Scene(vbox, 400, 350);
		addProductStage.setScene(scene);
		addProductStage.show();
	}
	
	private void confirmButtonClicked(ActionEvent event) {
		if(validateInput()) {
			ShoppingCartSystem.instance().addProduct(productNameTF.getText(), productBrandTF.getText(), productCategoryTF.getText(),
					productPriceTF.getText(), productQuantityTF.getText());
			clearFields();
			addProductStage.close();
		}
	}
	
	private void cancelButtonClicked(ActionEvent event) {
		addProductStage.close();
	}
	
	private boolean validateInput() {
		boolean checkProductNameInput = displayProductNameTF(ShoppingCartSystem.instance().checkProductNameField(productNameTF.getText()));
		boolean checkBrandNameInput = displayBrandNameTF(ShoppingCartSystem.instance().checkProductBrandField(productBrandTF.getText()));
		boolean checkProductCategoryInput = displayProductCategoryTF(ShoppingCartSystem.instance().checkProductCategoryField(productCategoryTF.getText()));
		boolean checkProductPriceInput = displayPriceTF(ShoppingCartSystem.instance().checkProductPriceField(productPriceTF.getText()));
		boolean checkProductQuantityInput = displayQuantityTF(ShoppingCartSystem.instance().checkProductQuantityField(productQuantityTF.getText()));
		
		return checkProductNameInput && checkBrandNameInput && checkProductCategoryInput && checkProductPriceInput && checkProductQuantityInput;
	}
	
	private boolean displayProductNameTF(boolean validateProductName) {
		if(!validateProductName) {
			productNameTF.setStyle("-fx-border-color: red;");
			productNameL.setText("Product Name: CAN'T BE LEFT EMPTY");
			return false;
		} else {
			productNameTF.setStyle("-fx-border-color: none;");
			productNameL.setText("Product Name:");
			return true;
		}
	}
	
	private boolean displayBrandNameTF(boolean validateProductBrand) {
		if(!validateProductBrand) {
			productBrandTF.setStyle("-fx-border-color: red;");
			productBrandL.setText("Product Brand: CAN'T BE LEFT EMPTY");
			return false;
		} else {
			productBrandTF.setStyle("-fx-border-color: none;");
			productBrandL.setText("Product Brand:");
			return true;
		}
	}
	
	private boolean displayProductCategoryTF(boolean validateProductCategory) {
		if(!validateProductCategory) {
			productCategoryTF.setStyle("-fx-border-color: red;");
			productCategoryL.setText("Product Category: CAN'T BE LEFT EMPTY");
			return false;
		} else {
			productCategoryTF.setStyle("-fx-border-color: none;");
			productCategoryL.setText("Product Category:");
			return true;
		}
	}
	
	private boolean displayPriceTF(boolean validateProductPrice) {
		if(!validateProductPrice) {
			productPriceTF.setStyle("-fx-border-color: red;");
			productPriceL.setText("Product Price: INVALID, MAKE SURE PRICE IS NUMERICAL!");
			return false;
		} else {
			productPriceTF.setStyle("-fx-border-color: none;");
			productPriceL.setText("Product Price:");
			return true;
		}
	}
	
	private boolean displayQuantityTF(boolean validateProductQuantity) {
		if(!validateProductQuantity) {
			productQuantityTF.setStyle("-fx-border-color: red;");
			productQuantityL.setText("Product Quantity: INVALID, HAS TO BE A WHOLE NUMBER!");
			return false;
		} else {
			productQuantityTF.setStyle("-fx-border-color: none;");
			productQuantityL.setText("Product Quantity:");
			return true;
		}
	}
	
	private void clearFields() {
		productNameTF.clear();
		productBrandTF.clear();
		productCategoryTF.clear();
		productPriceTF.clear();
		productQuantityTF.clear();
	}
}
