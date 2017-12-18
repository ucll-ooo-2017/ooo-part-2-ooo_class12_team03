package controller;

import model.Die;
import view.DieView;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class DieController extends Observable {

	private Random random;
	private Die model;
	private DieView view;

	public DieController(Die model, DieView view) {
		setModel(model);
		setView(view);
		addObserver(view);
		random = new Random();
	}

	public void roll() {
		model.setValue(random.nextInt(6) + 1);
		setChanged();
		notifyObservers(model);
	}

	public Die getModel() {
		return model;
	}
	public void setModel(Die model) {
		this.model = model;
	}

	public DieView getView() {
		return view;
	}
	public void setView(DieView view) {
		this.view = view;
	}
}
