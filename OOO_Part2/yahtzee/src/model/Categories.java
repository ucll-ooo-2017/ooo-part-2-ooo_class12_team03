package model;

import java.util.ArrayList;

public enum Categories {
	ACES("Aces", 1),
	TWOS("Twos", 2),
	THREES("Threes", 3),
	FOURS("Fours", 4),
	FIVES("Fives", 5),
	SIXES("Sixes", 6),
	THREE_OF_A_KIND("Three of a kind", 3),
	FOUR_OF_A_KIND("Four of a kind", 4),
	FULL_HOUSE("Full house", 25),
	SMALL_STRAIGHT("Small straight", 30),
	LARGE_STRAIGHT("Large straight", 40),
	YAHTZEE("Yahtzee", 50),
	CHANCE("Chance", 0);

	private String name;
	private int points;

	Categories(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}
	public int getPoints() {
		return points;
	}

	public static ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<>(values().length);
		for (Categories category : values()) {
			names.add(category.name);
		}
		return names;
	}

	public static Categories find(String name) {
		for (Categories category : values()) {
			if (category.getName().equalsIgnoreCase(name)) {
				return category;
			}
		}
		return null;
	}
}
