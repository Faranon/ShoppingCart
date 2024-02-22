package facade;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddQuantityGUI {
	private Stage addQuantityStage = new Stage();
	private Label quantityL = new Label("Quantity");
	private TextField quantityTF = new TextField();
	
	private String selectedProduct;
	private ObservableList<String> observableCart;
	private Label totalL;
	
	public AddQuantityGUI(String selectedProduct, ObservableList<String> observableCart,
			Label totatL) {
		addQuantityStage.setTitle("Adding Quantity");
		
		this.selectedProduct = selectedProduct;
		this.observableCart = observableCart;
		this.totalL = totatL;
		
		Button cancelButton = new Button("Cancel");
		Button confirmButton = new Button("Confirm");
		
		// actions taken when either cancel or confirm button is clicked
		confirmButton.setOnAction(this::confirmButtonClicked);
		cancelButton.setOnAction(this::cancelButtonClicked);
		
		// GUI layout
		HBox hBoxButton = new HBox();
		VBox vBox = new VBox();
		
		hBoxButton.getChildren().addAll(cancelButton, confirmButton);
		vBox.getChildren().addAll(quantityL, quantityTF, hBoxButton);
		
		Scene scene = new Scene(vBox, 300, 100);
		addQuantityStage.setScene(scene);
	}
	
	public void showAddProductGUI() {
		addQuantityStage.show();
	}
	
	
	private void confirmButtonClicked(ActionEvent event) {
		String[] splitProduct = selectedProduct.split(" \\| ");
		int productCap = Integer.parseInt(splitProduct[5]);
		boolean checkQuantityInput, checkOverCap;
		
		checkQuantityInput = displayQuantityTF(ShoppingCartSystem.instance().validateQuantity(productCap,
				quantityTF.getText()));
		
		if(checkQuantityInput) {
			String quantityInput = quantityTF.getText();
			int convertQuantity = Integer.parseInt(quantityInput);
			
			checkOverCap = displayQuantityTF(ShoppingCartSystem.instance().addToCart(splitProduct,
					observableCart, convertQuantity, productCap));
			
			if(checkOverCap) {
				String totalCartPrice;
				totalCartPrice = ShoppingCartSystem.instance().getCartTotalPrice(observableCart);
				
				totalL.setText("Total: " + totalCartPrice);
				
				addQuantityStage.close();
			}
		}
	}
	
	private void cancelButtonClicked(ActionEvent event) {
		addQuantityStage.close();
	}
	
	private boolean displayQuantityTF(boolean validatePQuantity) {
		if(!validatePQuantity) {
			quantityTF.setStyle("-fx-border-color: red;");
			quantityL.setText("Product Quantity: INVALID");
			return false;
		} else {
			quantityTF.setStyle("-fx-border-color: none;");
			quantityL.setText("Quantity:");
			return true;
		}
	}
	
}