package main;

public abstract class GameObject {
	private String name;
	private String pre;
	private boolean moveable;

	public GameObject(String name, String pre, boolean moveable) {
		super();
		this.name = name;
		this.pre = pre;
		this.moveable = moveable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

}
