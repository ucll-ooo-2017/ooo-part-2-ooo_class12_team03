package controller;

import model.Die;
import model.Player;
import view.DieView;
import view.InputPane;
import view.YahtzeePane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;

public class Controller extends Observable {

	private InputPane inputPane;

	private int playerCount = 0;
	private ArrayList<PlayerController> players;

	private ArrayList<DieController> dice;

	public Controller() {
		players = new ArrayList<>(playerCount);
		dice = new ArrayList<>(5);
		for (int i = 5; i > 0; --i) {
			dice.add(new DieController(new Die(), new DieView()));
		}

		do {
			try {
				playerCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of players:"));
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Invalid amount");
			}
		} while(playerCount < 2);

		inputPane = new InputPane(this);
		inputPane.show();
	}

	public void handleInputOK(String input) {
		if (input == null || input.trim().isEmpty()) {
			return;
		}
		for (PlayerController player : players) {
			if (player.getModel().getName().equalsIgnoreCase(input)) {
				return;
			}
		}
		createPlayer(input);
	}

	public void handleInputCancel() {
		inputPane.close();
	}

	private void createPlayer(String name) {
		players.add(new PlayerController(
				new Player(name),
				new YahtzeePane(this)
		));

		if (players.size() >= playerCount) {
			inputPane.close();

			startGame();
		}
	}

	private void startGame() {
		for (PlayerController player : players) {
			YahtzeePane view = player.getView();
			view.show();
			view.update(this, players.get(0).getModel());
		}
	}

	public void rollDice() {
		for (DieController dieController : dice) {
			dieController.roll();
		}
	}

	public ArrayList<DieController> getDice() {
		return dice;
	}
}
