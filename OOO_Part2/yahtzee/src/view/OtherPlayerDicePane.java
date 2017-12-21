package view;

import controller.DieAsideController;
import controller.DieController;
import controller.PlayerController;
import controller.YahtzeeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import model.Die;

import java.util.ArrayList;
import java.util.Observable;

public class OtherPlayerDicePane extends GridPane {

	private static OtherPlayerDicePane instance;
	private static Object mutex = new Object();

	private ArrayList<DieView> dice;

	public OtherPlayerDicePane() {
		this.dice = new ArrayList<>(5);
		for (int i = 0; i < 5; ++i) {
			dice.add(new DieView());
		}

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10));
		this.setAlignment(Pos.CENTER_LEFT);

		int pos = 0;
		for (DieView die : dice) {
			add(die, pos, 0);
			++pos;
		}
	}

	public void update() {
		PlayerController activePlayer = YahtzeeController.getInstance().getActivePlayer();

		int i = 0;
		for (DieController dieController : activePlayer.getDieControllers()) {
			if (dieController.getModel().getValue() != 0) {
				dice.get(i).update(null, dieController.getModel());
				++i;
			}
		}
		if (i < dice.size()) {
			for (DieAsideController dieAsideController : activePlayer.getDieAsideControllers()) {
				if (dieAsideController.getModel().getValue() != 0) {
					dice.get(i).update(null, dieAsideController.getModel());
					if (++i >= dice.size()) {
						break;
					}
				}
			}
		}
	}
}
