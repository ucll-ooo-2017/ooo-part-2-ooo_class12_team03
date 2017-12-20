package view;

import controller.YahtzeeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InputPane extends GridPane {

	private YahtzeeController yahtzeeController;

	private TextField fieldName;

	private Stage stage;

	public InputPane(YahtzeeController yahtzeeController) {
		this.yahtzeeController = yahtzeeController;

		this.setHgap(10);
		this.setVgap(10);
		this.setAlignment(Pos.BASELINE_CENTER);
		this.setPadding(new Insets(10));

		add(new Text("Name:"), 0, 0);
		fieldName = new TextField();
		add(fieldName, 1, 0);

		Button buttonOK = new Button("OK");
		buttonOK.setOnAction(new OKButtonHandler());
		add(buttonOK, 0, 1);
		Button buttonCancel = new Button("Cancel");
		buttonCancel.setOnAction(new CancelButtonHandler());
		add(buttonCancel, 1, 1);

		stage = new Stage();
		stage.setTitle("Input");
		stage.setScene(new Scene(this));
	}

	public void show() {
		stage.show();
	}

	public void close() {
		stage.close();
	}

	public void clearFields() {
		fieldName.clear();
	}

	class OKButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			yahtzeeController.handleInputOK(fieldName.getText());
			clearFields();
		}
	}

	class CancelButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			yahtzeeController.handleInputCancel();
		}
	}
}
