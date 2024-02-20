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

public class RemoveQuantityGUI {
	private Stage removeQuantityStage = new Stage();
	private Label quantityL = new Label("Quantity");
	private TextField quantityTF = new TextField();
	
	private String selectedProduct;
	private ObservableList<String> observableShoppingCart;
	private Label totalL;
	
	public RemoveQuantityGUI(String selectedProduct, ObservableList<String> observableShoppingCart,
			Label totatL) {
		removeQuantityStage.setTitle("Removing Quantity");
		
		this.selectedProduct = selectedProduct;
		this.observableShoppingCart = observableShoppingCart;
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
		removeQuantityStage.setScene(scene);
	}
	
	public void showRemoveQuantityGUI() {
		removeQuantityStage.show();
	}
	
	
	private void confirmButtonClicked(ActionEvent event) {
		String[] splitProduct = selectedProduct.split(" \\| ");
		int productCap = Integer.parseInt(splitProduct[5]);
		boolean checkQuantityInput;
		
		checkQuantityInput = displayQuantityTF(ShoppingCartSystem.instance().validateQuantity(productCap,
				quantityTF.getText()));
		
		if(checkQuantityInput) {
			String quantityInput = quantityTF.getText();
			int convertQuantity = Integer.parseInt(quantityInput);
			
			ShoppingCartSystem.instance().removeFromCart(splitProduct,
					observableShoppingCart, convertQuantity);
			
			String totalCartPrice;
			totalCartPrice = ShoppingCartSystem.instance().getCartTotalPrice(observableShoppingCart);
			
			totalL.setText("Total: " + totalCartPrice);
			
			removeQuantityStage.close();
		}
	}
	
	private void cancelButtonClicked(ActionEvent event) {
		removeQuantityStage.close();
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