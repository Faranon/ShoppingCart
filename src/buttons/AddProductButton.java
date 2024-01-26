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
	
	private Label productNameL = new Label("Product Name:");
	private Label productCategoryL = new Label("Product Category:");
	private Label productBrandL = new Label("Product Brand:");
	private Label productPriceL = new Label("Product Price:");
	private Label productQuantityL = new Label("Product Quantity:");
	
	private double productPrice;
	private int productQuantity;
	
	private Stage addProductStage = new Stage();
	
	public AddProductButton(String buttonName) {
		super(buttonName);
	}

	@Override
	public void handle(ActionEvent arg0) {
		Button cancelButton = new Button("Cancel");
		Button confirmButton = new Button("Confirm");
		
		confirmButton.setOnAction(this::confirmButtonClicked);
		cancelButton.setOnAction(this::cancelButtonClicked);
		
		VBox vbox = new VBox(10);
		HBox hbox = new HBox(100);
		
		hbox.getChildren().addAll(cancelButton, confirmButton);
		
		vbox.getChildren().addAll(productNameL, productNameTF, productCategoryL, productCategoryTF,
				productBrandL, productBrandTF, productPriceL, productPriceTF, productQuantityL,
				productQuantityTF, hbox);
		
		Scene scene = new Scene(vbox, 400, 350);
		addProductStage.setScene(scene);
		addProductStage.show();
	}
	
	private void confirmButtonClicked(ActionEvent event) {
		if(validateInput()) {
			ShoppingCartSystem.getInstance().addProduct(productNameTF.getText(), productBrandTF.getText(),
					productCategoryTF.getText(), productPrice, productQuantity);
			
			clearFields();
			
			addProductStage.close();
		}
	}
	
	private void cancelButtonClicked(ActionEvent event) {
		addProductStage.close();
	}
	
	private boolean validateInput() {
		boolean checkPriceInput = validatePriceInput(productPriceTF);
		boolean checkQuantityInput = validateQuantityInput(productQuantityTF);
		boolean checkProductNameInput = checkProductNameTF();
		boolean checkProductCategoryInput = checkProductCategoryTF();
		boolean checkBrandNameInput = checkBrandNameTF();
		return checkProductNameInput && checkProductCategoryInput && checkBrandNameInput &&
				checkPriceInput && checkQuantityInput;
	}
	
	private boolean checkProductNameTF() {
		if(productNameTF.getText().isEmpty()) {
			productNameTF.setStyle("-fx-border-color: red;");
			productNameL.setText("Product Name: CAN'T BE LEFT EMPTY");
			return false;
		} else {
			productNameTF.setStyle("-fx-border-color: none;");
			productNameL.setText("Product Name:");
			return true;
		}
	}
	
	private boolean checkProductCategoryTF() {
		if(productCategoryTF.getText().isEmpty()) {
			productCategoryTF.setStyle("-fx-border-color: red;");
			productCategoryL.setText("Product Category: CAN'T BE LEFT EMPTY");
			return false;
		} else {
			productCategoryTF.setStyle("-fx-border-color: none;");
			productCategoryL.setText("Product Category:");
			return true;
		}
	}
	
	private boolean checkBrandNameTF() {
		if(productBrandTF.getText().isEmpty()) {
			productBrandTF.setStyle("-fx-border-color: red;");
			productBrandL.setText("Product Brand: CAN'T BE LEFT EMPTY");
			return false;
		} else {
			productBrandTF.setStyle("-fx-border-color: none;");
			productBrandL.setText("Product Brand:");
			return true;
		}
	}
	
	private boolean validatePriceInput(TextField priceTF) {
		try {
			productPrice = Double.valueOf(productPriceTF.getText());
			priceTF.setStyle("-fx-border-color: none;");
			productPriceL.setText("Product Price:");
			return true;
		} catch(NumberFormatException e) {
			priceTF.setStyle("-fx-border-color: red;");
			productPriceL.setText("Product Price: INVALID, MAKE SURE PRICE IS NUMERICAL!");
			return false;
		}
	}
	
	private boolean validateQuantityInput(TextField quantityTF) {
		try {
			productQuantity = Integer.parseInt(productQuantityTF.getText());
			quantityTF.setStyle("-fx-border-color: none;");
			productQuantityL.setText("Product Quantity:");
			return true;
		} catch(NumberFormatException e) {
			quantityTF.setStyle("-fx-border-color: red;");
			productQuantityL.setText("Product Quantity: INVALID, HAS TO BE A WHOLE NUMBER!");
			return false;
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
