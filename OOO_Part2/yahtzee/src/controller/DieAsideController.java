package controller;

import model.Die;
import view.DieAsideView;

import java.util.Observable;

public class DieAsideController extends Observable {

	private Die model;
	private DieAsideView view;

	public DieAsideController(PlayerController playerController) {
		this.model = new Die(0);
		this.view = new DieAsideView(playerController, this);
		this.view.setImage(Die.getImage(this.model.getValue()));
		addObserver(this.view);
	}

	public Die getModel() {
		return model;
	}
	public DieAsideView getView() {
		return view;
	}

	public void setAside(DieController dieController) {
		model.setValue(dieController.getModel().getValue());
		update();
	}

	public void unsetAside() {
		model.setValue(0);
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
