package controller;

import model.Player;
import view.YahtzeePane;

public class PlayerController {

	private Player model;
	private YahtzeePane view;

	public PlayerController(Player model, YahtzeePane view) {
		this.model = model;
		this.view = view;
	}

	public YahtzeePane getView() {
		return view;
	}

	public Player getModel() {
		return model;
	}
}
