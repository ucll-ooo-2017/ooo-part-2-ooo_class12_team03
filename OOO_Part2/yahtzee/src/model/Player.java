package model;

public class Player {

	private String name;

	public Player(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Player name cannot be empty");
		}
		this.name = name;
	}
}
