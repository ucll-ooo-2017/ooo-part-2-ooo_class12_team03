package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class InputPane extends GridPane {

	private Controller controller;

	private TextField fieldName;

	public InputPane(Controller controller) {
		this.controller = controller;

		this.setHgap(10);
		this.setVgap(10);
		this.setAlignment(Pos.BASELINE_CENTER);
		this.setPadding(new Insets(10, 10, 10, 10));

		Text textName = new Text("Name:");
		add(textName, 0, 0);
		fieldName = new TextField();
		add(fieldName, 1, 0);

		Button buttonOK = new Button("OK");
		buttonOK.setOnAction(new OKButtonHandler());
		add(buttonOK,0,1);
		Button buttonCancel = new Button("Cancel");
		buttonCancel.setOnAction(new CancelButtonHandler());
		add(buttonCancel, 1, 1);
	}

	public void clearFields() {
		fieldName.clear();
	}

	class OKButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.handleInputOK(fieldName.getText());
			clearFields();
		}
	}

	class CancelButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			clearFields();
		}
	}
}
