package controller;

import model.Die;
import view.DieView;

import java.util.Observable;

public class DieAsideController extends Observable {

	private PlayerController playerController;

	private Die model;
	private DieView view;

	public DieAsideController(PlayerController playerController) {
		this.playerController = playerController;
		this.model = new Die(0);
		this.view = new DieView();
		this.view.setImage(Die.getImage(this.model.getValue()));
		addObserver(this.view);
	}

	public Die getModel() {
		return model;
	}
	public DieView getView() {
		return view;
	}

	public void setAside(DieController dieController) {
		model.setValue(dieController.getModel().getValue());
		update();
	}

	public void reset() {
		model.setValue(0);
		update();
	}

	public void update() {
		setChanged();
		notifyObservers(model);
		YahtzeeController.getInstance().updateOtherPlayerDicePanes();
	}
}
