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

public class LoginGUI {
	private Stage loginStage = new Stage();
	private Stage primaryStage;
	private ObservableList<String> observableProducts;
	
	private Label userNameL = new Label("Username:");
	private Label passwordL = new Label("Password:");
	private TextField userNameTF = new TextField();
	private TextField passwordTF = new TextField();
	
	LoginGUI(ObservableList<String> observableProducts, Stage primaryStage) {
		loginStage.setTitle("Login");
		this.observableProducts = observableProducts;
		this.primaryStage = primaryStage;
		
		Button cancelButton = new Button("Cancel");
		Button confirmButton = new Button("Confirm");
		
		// actions taken when either cancel or confirm button is clicked
		confirmButton.setOnAction(this::confirmButtonClicked);
		cancelButton.setOnAction(this::cancelButtonClicked);
		
		// GUI layout
		HBox buttonHBox = new HBox();
		buttonHBox.getChildren().addAll(cancelButton, confirmButton);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(userNameL, userNameTF, passwordL, passwordTF, buttonHBox);
		
		Scene scene = new Scene(vBox, 300, 110);
		loginStage.setScene(scene);
	}
	
	public void showLoginGUI() {
		loginStage.show();
	}
	
	private void confirmButtonClicked(ActionEvent e) {
		boolean checkUsername, checkPassword;
		checkUsername = displayUsername(ShoppingCartSystem.instance().checkUsername(userNameTF.getText()));
		checkPassword = displayPassword(ShoppingCartSystem.instance().checkPassword(passwordTF.getText()));
		
		if(checkUsername && checkPassword) {
			ShoppingCartSystem.instance().openInitializationGUI(observableProducts, primaryStage);
			loginStage.close();
		}
	}
	
	private void cancelButtonClicked(ActionEvent e) {
		loginStage.close();
	}
	
	private boolean displayUsername(boolean validateUsername) {
		if(!validateUsername) {
			userNameTF.setStyle("-fx-border-color: red;");
			userNameL.setText("Username: INCORRECT");
			return false;
		} else {
			userNameTF.setStyle("-fx-border-color: none;");
			userNameL.setText("Username:");
			return true;
		}
	}
	
	private boolean displayPassword(boolean validatePassword) {
		if(!validatePassword) {
			passwordTF.setStyle("-fx-border-color: red;");
			passwordL.setText("Password: INCORRECT");
			return false;
		} else {
			passwordTF.setStyle("-fx-border-color: none;");
			passwordL.setText("Password:");
			return true;
		}
	}
}
