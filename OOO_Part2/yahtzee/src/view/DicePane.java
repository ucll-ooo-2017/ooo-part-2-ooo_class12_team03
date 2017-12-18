package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

public class DicePane extends GridPane implements Observer {

	private Controller controller;

	private Button rollButton;

	public DicePane(Controller controller) {
		this.controller = controller;

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10));

		rollButton = new Button();
		rollButton.setText("Roll Dice");
		rollButton.setOnAction(new ClickHandler());
		add(rollButton, 0, 0);
	}

	@Override
	public void update(Observable o, Object arg) {
		//
	}

	class ClickHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.rollDice();
		}
	}
}
