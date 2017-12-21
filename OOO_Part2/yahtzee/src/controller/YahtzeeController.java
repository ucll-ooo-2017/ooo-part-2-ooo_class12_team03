package controller;

import view.InputPane;
import view.OtherPlayerDicePane;
import view.YahtzeePane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;

public class YahtzeeController extends Observable {

	private static YahtzeeController instance;
	private static Object mutex = new Object();

	private InputPane inputPane;

	private int playerCount = 0;
	private ArrayList<PlayerController> playerControllers;
	private PlayerController activePlayer;

	private YahtzeeController() {
	}

	public static YahtzeeController getInstance() {
		if (instance == null) {
			synchronized (mutex) {
				if (instance == null) {
					instance = new YahtzeeController();
				}
			}
		}
		return instance;
	}

	public void run() {
		do {
			try {
				playerCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of players:"));
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Invalid amount");
			}
		} while(playerCount < 2);

		playerControllers = new ArrayList<>(playerCount);

		inputPane = new InputPane(this);
		inputPane.show();
	}

	public void handleInputOK(String input) {
		if (input == null || input.trim().isEmpty()) {
			return;
		}
		for (PlayerController playerController : playerControllers) {
			if (playerController.getModel().getName().equalsIgnoreCase(input)) {
				return;
			}
		}
		createPlayer(input);
	}

	public void handleInputCancel() {
		inputPane.close();
	}

	private void createPlayer(String name) {
		PlayerController playerController = new PlayerController(name);
		playerControllers.add(playerController);
		addObserver(playerController.getView());
		addObserver(playerController.getView().getDicePane());
		if (playerControllers.size() >= playerCount) {
			inputPane.close();
			startGame();
		}
	}

	private void startGame() {
		setActivePlayer(playerControllers.get(0));
		activePlayer.rollDice();
	}

	public PlayerController getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(PlayerController activePlayer) {
		this.activePlayer = activePlayer;
		for (PlayerController playerController : playerControllers) {
			YahtzeePane view = playerController.getView();
			view.show();
			view.update(playerController, activePlayer.getModel());
		}
		setChanged();
		notifyObservers(activePlayer);
	}

	public void nextPlayer() {
		PlayerController nextPlayer = playerControllers.get(
			playerControllers.indexOf(activePlayer) + 1 >= playerControllers.size() ?
			0 :
			playerControllers.indexOf(activePlayer) + 1
		);
		setActivePlayer(nextPlayer);
		activePlayer.resetBoard();
		activePlayer.rollDice();
	}

	public void updateOtherPlayerDicePanes() {
		for (PlayerController playerController : playerControllers) {
			playerController.getOtherPlayerDicePane().update();
		}
	}
}
