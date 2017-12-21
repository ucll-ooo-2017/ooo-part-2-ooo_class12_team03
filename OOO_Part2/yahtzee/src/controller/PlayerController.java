package controller;

import model.Categories;
import model.Player;
import view.OtherPlayerDicePane;
import view.YahtzeePane;

import java.util.ArrayList;
import java.util.Observable;

public class PlayerController extends Observable {

	private Player model;
	private YahtzeePane view;

	private ArrayList<DieController> dieControllers;
	private ArrayList<DieAsideController> dieAsideControllers;

	private OtherPlayerDicePane otherPlayerDicePane;

	private int diceRolled = 0;
	public static int DICE_ROLLED_MAX = 3;

	public PlayerController(String name) {
		this.model = new Player(name);
		this.otherPlayerDicePane = new OtherPlayerDicePane();

		dieControllers = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			dieControllers.add(new DieController(this));
		}
		dieAsideControllers = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			dieAsideControllers.add(new DieAsideController(this));
		}

		this.view = new YahtzeePane(this);
	}

	public void rollDice() {
		diceRolled++;
		for (DieController dieController : dieControllers) {
			dieController.roll();
		}

		if (diceRolled >= DICE_ROLLED_MAX) {
			endTurn();
		}
	}

	public void setDieAside(DieController dieController) {
		for (DieAsideController dieAsideController : dieAsideControllers) {
			if (dieAsideController.getModel().getValue() == 0) {
				dieAsideController.setAside(dieController);
				dieController.setAside();
				break;
			}
		}
	}

	public void endTurn() {
		// Set all die still on the board aside, disallowing any further rolling of the dice
		for (DieController dieController : dieControllers) {
			if (dieController.getModel().getValue() != 0) {
				setDieAside(dieController);
			}
		}
		// Will disable rolling button
		diceRolled = DICE_ROLLED_MAX;
		view.getDicePane().update(null, this); // We'll need to update the view
	}

	public void resetBoard() {
		diceRolled = 0;
		view.getDicePane().update(null, this); // We'll need to update the view
		for (DieAsideController dieAsideControllerController : dieAsideControllers) {
			dieAsideControllerController.reset();
		}
		for (DieController dieController : dieControllers) {
			dieController.reset();
		}
	}

	public void setCategory(Categories category) {
		// TODO: Give score etc.

		YahtzeeController.getInstance().nextPlayer();
	}

	public boolean canRoll() {
		return diceRolled < DICE_ROLLED_MAX;
	}

	public Player getModel() {
		return model;
	}
	public YahtzeePane getView() {
		return view;
	}
	public ArrayList<DieController> getDieControllers() {
		return dieControllers;
	}
	public ArrayList<DieAsideController> getDieAsideControllers() {
		return dieAsideControllers;
	}
	public OtherPlayerDicePane getOtherPlayerDicePane() {
		return otherPlayerDicePane;
	}
}
