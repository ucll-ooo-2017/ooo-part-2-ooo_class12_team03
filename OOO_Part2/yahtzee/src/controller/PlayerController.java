package controller;

import model.Player;
import view.YahtzeePane;

import java.util.ArrayList;
import java.util.Observable;

public class PlayerController extends Observable {

	private Player model;
	private YahtzeePane view;

	private ArrayList<DieController> dieControllers;

	public PlayerController(String name) {
		this.model = new Player(name);

		dieControllers = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			dieControllers.add(new DieController(this));
		}

		this.view = new YahtzeePane(this);
	}

	public void rollDice() {
		for (DieController dieController : dieControllers) {
			dieController.roll();
		}
	}

	public void setDieAside(DieController dieController) {
		System.out.println(model.getName() + " clicked die with value " + dieController.getModel().getValue());
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
}
