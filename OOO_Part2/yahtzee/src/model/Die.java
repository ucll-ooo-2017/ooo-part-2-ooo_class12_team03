package model;

import javafx.scene.image.Image;

// Dice = plural, learn English scrubs
public class Die {

	private static final Image[] images = {
			new Image("resources/die/side1.png"), // 1
			new Image("resources/die/side2.png"), // 2
			new Image("resources/die/side3.png"), // 3
			new Image("resources/die/side4.png"), // 4
			new Image("resources/die/side5.png"), // 5
			new Image("resources/die/side6.png"), // 6
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
