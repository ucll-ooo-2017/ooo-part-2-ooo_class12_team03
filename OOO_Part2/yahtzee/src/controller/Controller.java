package controller;

import model.Player;
import view.InputPane;
import view.YahtzeePane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;

public class Controller extends Observable {

	private InputPane inputPane;

	private int playerCount = 0;
	private ArrayList<PlayerController> players;

	public Controller() {
		do {
			try {
				playerCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of players:"));
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Invalid amount");
			}
		} while(playerCount < 2);

		players = new ArrayList<>(playerCount);

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
}
