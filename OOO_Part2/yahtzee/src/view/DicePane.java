package view;

import controller.DieController;
import controller.PlayerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Observable;
import java.util.Observer;

public class DicePane extends GridPane implements Observer {

	private PlayerController playerController;

	private Button rollButton;

	public DicePane(PlayerController playerController) {
		this.playerController = playerController;

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10));

		rollButton = new Button("Roll Dice");
		rollButton.setOnAction(new ClickHandler());
		add(rollButton, 0, 1);

		int pos = 0;
		for (DieController dieController : playerController.getDieControllers()) {
			add(dieController.getView(), pos, 2);
			pos++;
		}
	}

	@Override
	public void update(Observable controller, Object o) {
		//
	}

	class ClickHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			playerController.rollDice();
		}
	}
}
