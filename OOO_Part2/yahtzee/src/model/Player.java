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

	/* ADD Exception if Name Length equals 0  or PLayer name is not unique*/
	public void setName(String name) {
		this.name = name;
	}

}

