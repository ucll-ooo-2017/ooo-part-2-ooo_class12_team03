package model;

import javafx.scene.image.Image;

public class Die {

	private static final Image[] images = {
		new Image("resources/die/side0.png"), // Invisible (eg: set aside)
		new Image("resources/die/side1.png"), // 1
		new Image("resources/die/side2.png"), // 2
		new Image("resources/die/side3.png"), // 3
		new Image("resources/die/side4.png"), // 4
		new Image("resources/die/side5.png"), // 5
		new Image("resources/die/side6.png"), // 6
	};

	private int value;

	public Die() {
		this.value = 0;
	}
	public Die(int value) {
		setValue(value);
	}

	public static Image getImage(int value) {
		return images[value];
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		if (value < 0 || value > 6) {
			throw new IllegalArgumentException("Die value must be between 0 and 6 (0 being a null image)");
		}
		this.value = value;
	}
}
