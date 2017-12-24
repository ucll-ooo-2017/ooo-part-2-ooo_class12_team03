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

	private boolean setAside = false;

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
	public PlayerController getPlayerController() {
		return playerController;
	}
	public boolean isSetAside() {
		return setAside;
	}

	public void roll() {
		if (!setAside) {
			model.setValue(random.nextInt(6) + 1);
			update();
		}
	}

	public void setAside() {
		setAside = true;
		model.setValue(0);
		update();
	}

	public void unsetAside(DieAsideController dieAsideController) {
		model.setValue(dieAsideController.getModel().getValue());
		update();
	}

	public void reset() {
		setAside = false;
		roll();
	}

	public void update() {
		setChanged();
		notifyObservers(model);
		YahtzeeController.getInstance().updateOtherPlayerDicePanes();
	}
}
