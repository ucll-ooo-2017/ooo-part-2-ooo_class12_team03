package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Die;

import java.util.Observable;
import java.util.Observer;

public class DieView extends ImageView implements Observer {

	public DieView() {
	}

	public void update(Observable controller, Object o) {
		if (o instanceof Die) {
			this.setImage(Die.getImage(((Die) o).getValue()));
		}
	}
}
