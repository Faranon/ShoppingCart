package buttons;

import facade.ShoppingCartSystem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class CheckoutButton extends GUIButton implements EventHandler<ActionEvent> {
	private ObservableList<String> observableProducts, observableCart;
	private Label totalL;
	
	public CheckoutButton(String buttonName, ObservableList<String> observableProducts,
			ObservableList<String> observableCart, Label totalL) {
		super(buttonName);
		
		this.observableProducts = observableProducts;
		this.observableCart = observableCart;
		this.totalL = totalL;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		ShoppingCartSystem.instance().checkOut(observableCart);
		observableProducts.clear();
		totalL.setText("Total: $0.00");
		ShoppingCartSystem.instance().sendObservableLP(observableProducts);
	}

}
