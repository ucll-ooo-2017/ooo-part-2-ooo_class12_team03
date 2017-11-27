package model;

public class Player {
	
	private String name;
	public int id;

	public Player(String name) {
		this.setName(name);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

}

