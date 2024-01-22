package buttons;

import entities.Product;
import facade.ShoppingCartSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddProductButton extends GUIButton implements EventHandler<ActionEvent>{
	public AddProductButton(String buttonName) {
		super(buttonName);
	}

	@Override
	public void handle(ActionEvent arg0) {
		Stage addProductStage = new Stage();
		
		Label productName = new Label("Product Name:");
		Label productCategory = new Label("Product Category:");
		Label productBrand= new Label("Product Brand:");
		Label productPrice = new Label("Product Price:");
		Label productQuantity = new Label("Product Quantity:");
		
		TextField productNameTF = new TextField();
		TextField productCategoryTF = new TextField();
		TextField productBrandTF = new TextField();
		TextField productPriceTF = new TextField();
		TextField productQuantityTF = new TextField();
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(productName, productNameTF, productCategory, productCategoryTF,
				productBrand, productBrandTF, productPrice, productPriceTF, productQuantity, productQuantityTF);
		
		Scene scene = new Scene(vbox, 300, 350);
		addProductStage.setScene(scene);
		addProductStage.show();
		
		String getProductName = productNameTF.getText();
		String getProductBrand = productBrandTF.getText();
		String getProductCategory= productCategoryTF.getText();
		String getProductPrice = productPriceTF.getText();
		String getProductQuantity = productQuantityTF.getText();
		
		double price = Double.valueOf(getProductPrice);
		int quantity = Integer.parseInt(getProductQuantity);
		
		//system.addProduct(getProductName, getProductBrand, getProductCategory, price, quantity);
		
		/*Stage tempStage = new Stage();
		Button tempButton = new Button("Temp button");
		tempButton.setOnAction(e -> System.out.println("test 22"));
		
		StackPane layout = new StackPane();
		layout.getChildren().add(tempButton);
		
		Scene scene = new Scene(layout, 200, 100);
		tempStage.setScene(scene);
		tempStage.show();*/
	}
}
