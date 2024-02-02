/*
 * AddProductGUI is a class that is created whenever the AddProduct button is clicked. 
 * This class is used to get user input when creating a product. It also changes color to
 * red and warns the user whenever there is an invalid input when the confirm button is
 * clicked.
 */
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
	// Text fields for each attribute of product
	private TextField productNameTF = new TextField();
	private TextField productBrandTF = new TextField();
	private TextField productCategoryTF = new TextField();
	private TextField productPriceTF = new TextField();
	private TextField productQuantityTF = new TextField();
	
	// Lebels for each attribute of product
	private Label productNameL = new Label("Product Name:");
	private Label productBrandL = new Label("Product Brand:");
	private Label productCategoryL = new Label("Product Category:");
	private Label productPriceL = new Label("Product Price:");
	private Label productQuantityL = new Label("Product Quantity:");
	
	// Stage used to display all text fields and labels
	private Stage addProductStage = new Stage();
	
	// AddProductGUI constructor that creates the GUI of Add Product
	public AddProductGUI() {
		Button cancelButton = new Button("Cancel");
		Button confirmButton = new Button("Confirm");
		
		// actions that are taken when either the cancel or confirm button is clicked
		confirmButton.setOnAction(this::confirmButtonClicked);
		cancelButton.setOnAction(this::cancelButtonClicked);
		
		VBox vbox = new VBox(10);
		HBox hbox = new HBox(100);
		
		hbox.getChildren().addAll(cancelButton, confirmButton);
		
		vbox.getChildren().addAll(productNameL, productNameTF, productBrandL, 
				productBrandTF, productCategoryL, productCategoryTF, productPriceL,
				productPriceTF, productQuantityL, productQuantityTF, hbox);
		
		Scene scene = new Scene(vbox, 400, 350);
		addProductStage.setScene(scene);
		addProductStage.show();
	}
	
	/*
	 * This method gets called if the confirm button is clicked. It calls another method
	 * called validateInput() to check if all input string in the text fields are
	 * acceptable. If it is, the method calls the ShoppingCartSystem to add the product to
	 * the system, clears all the text fields, and closes the AddProductGUI.
	 */
	private void confirmButtonClicked(ActionEvent event) {
		if(validateInput()) {
			ShoppingCartSystem.instance().addProduct(productNameTF.getText(),
					productBrandTF.getText(), productCategoryTF.getText(),
					productPriceTF.getText(), productQuantityTF.getText());
			
			clearFields();
			addProductStage.close();
		}
	}
	
	/*
	 * This method gets called when the cancel button is clicked. It just closes the
	 * AddProductGUI and clears the text fields.
	 */
	private void cancelButtonClicked(ActionEvent event) {
		clearFields();
		addProductStage.close();
	}
	
	/*
	 * This method gets called inside the confirmButtonClicked method and checks each text
	 * field to see if it has the correct input. If it does, it's set to true otherwise it
	 * is false. The method then returns the && of each boolean expression, meaning that
	 * all boolean expressions must be true in order for it to return true, otherwise if
	 * one of them is false, it returns false.
	 */
	private boolean validateInput() {
		boolean checkProductNameInput, checkBrandNameInput, checkProductCategoryInput, 
			checkProductPriceInput, checkProductQuantityInput;
		
		checkProductNameInput = displayProductNameTF(ShoppingCartSystem.instance().checkProductName(productNameTF.getText()));
		checkBrandNameInput = displayBrandNameTF(ShoppingCartSystem.instance().checkProductBrand(productBrandTF.getText()));
		checkProductCategoryInput = displayProductCategoryTF(ShoppingCartSystem.instance().checkProductCategory(productCategoryTF.getText()));
		checkProductPriceInput = displayPriceTF(ShoppingCartSystem.instance().checkProductPrice(productPriceTF.getText()));
		checkProductQuantityInput = displayQuantityTF(ShoppingCartSystem.instance().checkProductQuantity(productQuantityTF.getText()));
		
		return checkProductNameInput && checkBrandNameInput && checkProductCategoryInput && checkProductPriceInput && checkProductQuantityInput;
	}
	
	/*
	 * The display methods gets called in the validateInput method. If the given
	 * boolean is false, it'll change the color of the text field to red and display a
	 * warning message. This lets the user know that the input is invalid and what needs 
	 * to be put in order for the input to be valid. If true, it removes the warning and
	 * changes the text field back to white.
	 */
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
	
	// Used to clear the text fields
	private void clearFields() {
		productNameTF.clear();
		productBrandTF.clear();
		productCategoryTF.clear();
		productPriceTF.clear();
		productQuantityTF.clear();
	}
}