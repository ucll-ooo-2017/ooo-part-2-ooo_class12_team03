package controller;

import model.Die;
import view.ClickableDieView;

import java.util.Observable;
import java.util.Random;

public class DieController extends Observable {

	private PlayerController playerController;

	private Die model;
	private ClickableDieView view;

	private Random random;

	public DieController(PlayerController playerController) {
		this.playerController = playerController;
		this.model = new Die();
		this.view = new ClickableDieView(playerController, this);
		addObserver(this.view);
		random = new Random();
	}

	public Die getModel() {
		return model;
	}
	public ClickableDieView getView() {
		return view;
	}

	public void roll() {
		model.setValue(random.nextInt(6) + 1);
		setChanged();
		notifyObservers(model);
	}
}
