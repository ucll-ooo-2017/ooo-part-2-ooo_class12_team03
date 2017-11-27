package controller;

import model.Player;
import view.PlayerView;

public class PlayerController {

	private Player model;
	private PlayerView view;
	
	public PlayerController(Player model, PlayerView view) {
		this.model = model;
		this.view = view;
	}
	
	public static void Show() {
		
	}

	public PlayerView getView() {
		return view;
	}

	public Player getModel() {
		return model;
	}
}
