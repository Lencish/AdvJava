package main;

public class Key extends GameObject {
	private int id;

	public Key(String name, String pre, boolean moveable, int id) {
		super(name, pre, moveable);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
