package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Die;

import java.util.Observable;
import java.util.Observer;

public class DieView implements Observer {

	private ImageView imageView;

	public DieView() {
		imageView = new ImageView();
	}

	public void update(Observable controller, Object o) {
		if (o instanceof Die) {
			imageView.setImage(Die.getImage(((Die) o).getValue()));
		}
	}
}
