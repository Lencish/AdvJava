package main;

public class Player {
	private int position;
	private Inventory playerInv;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Inventory getPlayerInv() {
		return playerInv;
	}
	
	public GameObject[] getItems() {
		return playerInv.getItems();
	}

	public void setPlayerInv(Inventory playerInv) {
		this.playerInv = playerInv;
	}
	
	public boolean addItem(GameObject toAdd) {
		return this.playerInv.addItem(toAdd);
	}
	
	public boolean removeItem(GameObject toRe) {
		return this.playerInv.removeItem(toRe);
	}
}
