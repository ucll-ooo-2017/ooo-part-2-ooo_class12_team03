package model;

import javafx.scene.image.Image;

// Dice = plural, learn English scrubs
public class Die {

	private static final Image[] images = {
			new Image(""), // 1
			new Image(""), // 2
			new Image(""), // 3
			new Image(""), // 4
			new Image(""), // 5
			new Image(""), // 6
	};

	private int value = 1;

	public Die() {
	}

	public static Image getImage(int value) {
		return images[value - 1];
	}

	public void setValue(int value) {
		if (value < 1 || value > 6) {
			throw new IllegalArgumentException("Die value must be between 1 and 6");
		}
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
