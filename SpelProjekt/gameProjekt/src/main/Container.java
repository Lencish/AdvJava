package main;

public class Container extends GameObject {

	private int keyU;
	private Key holdItem;
	private boolean locked;

	public Container(String name, String pre, boolean moveable, int keyU, Key holdItem) {
		super(name, pre, moveable);
		this.keyU = keyU;
		this.holdItem = holdItem;
		this.setLocked(true);
	}

	public int getKeyU() {
		return keyU;
	}

	public void setKeyU(int keyU) {
		this.keyU = keyU;
	}

	public Key getHoldItem() {
		return holdItem;
	}

	public void setHoldItem(Key holdItem) {
		this.holdItem = holdItem;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}
