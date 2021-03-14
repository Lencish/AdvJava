package main;

public class Room {
	private int id;
	private String name;
	private String pre;
	private Inventory roomInv;

	public Room(int id, String name, String pre) {
		super();
		this.id = id;
		this.name = name;
		this.pre = pre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String showRoom() {
		String re = this.name + "\n" + this.pre + "\nAnd you find \n";
		for (GameObject go : this.roomInv.getItems()) {
			if(go!=null) {
				re += go.getName()+"\n";
			}
		}
		return re;
	}

	public Inventory getRoomInv() {
		return roomInv;
	}

	public void setRoomInv(Inventory roomInv) {
		this.roomInv = roomInv;
	}
	
	public GameObject[] getItems() {
		return roomInv.getItems();
	}
	
	public boolean addItem(GameObject toAdd) {
		return this.roomInv.addItem(toAdd);
	}
	
	public boolean removeItem(GameObject toRe) {
		return this.roomInv.removeItem(toRe);
	}
}
