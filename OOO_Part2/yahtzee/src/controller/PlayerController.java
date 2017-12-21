package controller;

import model.Player;
import view.YahtzeePane;

import java.util.ArrayList;
import java.util.Observable;

public class PlayerController extends Observable {

	private Player model;
	private YahtzeePane view;

	private ArrayList<DieController> dieControllers;
	private ArrayList<DieAsideController> dieAsideControllers;

	public PlayerController(String name) {
		this.model = new Player(name);

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
		for (DieController dieController : dieControllers) {
			dieController.roll();
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
}
