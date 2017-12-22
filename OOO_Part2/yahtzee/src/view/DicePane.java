package view;

import controller.DieAsideController;
import controller.DieController;
import controller.PlayerController;
import controller.YahtzeeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Categories;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class DicePane extends GridPane implements Observer {

	private PlayerController playerController;

	private Button rollButton;
	private Button endTurnButton;
	private ComboBox<Categories> categoriesComboBox;

	public DicePane(PlayerController playerController) {
		this.playerController = playerController;

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10));

		rollButton = new Button("Roll Dice");
		rollButton.setOnAction(new RollButtonHandler());
		add(rollButton, 0, 1);
		GridPane.setVgrow(rollButton, Priority.ALWAYS);

		HBox diceBox = new HBox();
		diceBox.setSpacing(10);
		for (DieController dieController : playerController.getDieControllers()) {
			diceBox.getChildren().add(dieController.getView());
		}
		add(diceBox, 0, 2);
		GridPane.setVgrow(diceBox, Priority.ALWAYS);

		HBox asideDiceBox = new HBox();
		asideDiceBox.setSpacing(10);
		for (DieAsideController dieAsideController : playerController.getDieAsideControllers()) {
			asideDiceBox.getChildren().add(dieAsideController.getView());
		}
		add(asideDiceBox, 0, 0);
		GridPane.setVgrow(asideDiceBox, Priority.ALWAYS);

		categoriesComboBox = new ComboBox<>();
		categoriesComboBox.getItems().addAll(Categories.values());
		categoriesComboBox.setValue(categoriesComboBox.getItems().get(0));
		add(categoriesComboBox, 0, 3);
		GridPane.setVgrow(categoriesComboBox, Priority.NEVER);

		endTurnButton = new Button("OK");
		endTurnButton.setOnAction(new EndTurnButtonHandler());
		add(endTurnButton, 0, 4);
		GridPane.setVgrow(endTurnButton, Priority.NEVER);
	}

	@Override
	public void update(Observable controller, Object o) {
		if (o instanceof PlayerController) {
			PlayerController activePlayer = YahtzeeController.getInstance().getActivePlayer();
			rollButton.setVisible(playerController == activePlayer && playerController.canRoll());
		}

		// The only hashmap that would be sent to this is the rolls map
		if (o instanceof HashMap) {
			HashMap<String, Integer> rolls = (HashMap<String, Integer>) o;
			rolls.forEach((category, points) -> {
				categoriesComboBox.getItems().remove(Categories.find(category));
			});
		}
	}

	class RollButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			playerController.rollDice();
		}
	}

	class EndTurnButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			playerController.endTurn();
			playerController.setCategory(categoriesComboBox.getValue());
		}
	}
}
