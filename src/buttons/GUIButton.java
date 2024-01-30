package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public abstract class GUIButton extends Button implements EventHandler<ActionEvent> {

	public GUIButton(String buttonName) {
		super(buttonName);
		
		setOnAction(this);
	}

}