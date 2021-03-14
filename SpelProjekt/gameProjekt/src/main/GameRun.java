package main;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class GameRun {

	static Room[] rooms = { new Room(0, "Start Room", "A empty room with a locked door to out side."),
			new Room(1, "Dark room", "It is dark here, very hard to see."),
			new Room(2, "Shining room", "Too much light to see."),
			new Room(3, "Wareroom", "Many satff here, but nothing seems useful.") };

	static Npc[] npcs = { new Npc("Jason", "He is wearing a mask", 0, "Where is the way out?"),
			new Npc("Freddy ", "He has sharp nails", 1, "I want to sleep."),
			new Npc("Ture Sventon ", "A gentleman", 2, "Hi, do you know the way out?") };

	static Player player = new Player();

	public static boolean running = true;

	public static JTextArea msg2;
	public static JTextArea msg4;
	static String mode = "base";

	public void start() {
		UserInterface ui = new UserInterface();
		SwingUtilities.invokeLater(ui);

		setPlayer();
		setRooms();
		setNpcs();

		Update update = new Update();
		update.run();
	}

	private void setNpcs() {
		for (Npc npc : npcs) {
			npc.setNpvInv(new Inventory(1));
		}
		npcs[0].addItem(new Key("Bread", "just a small bit", true, 99));
	}

	private void setRooms() {
		for (Room r : rooms) {
			r.setRoomInv(new Inventory(3));
		}

		rooms[0].addItem(new Container("Locked door", "The door to outside", false, 0, null));
		rooms[1].addItem(
				new Container("Woodbox", "a wood box with lock", false, 1, new Key("IronKey", "a Iron Key", true, 2)));
		rooms[1].addItem(new Key("Glasses", "Someons glasses", true, 99));
		rooms[2].addItem(new Container("Ironbox", "a iron box", false, 2, new Key("GoldKey", "a Gold Key", true, 3)));
		rooms[2].addItem(new Key("Stick", "A wood Stick", true, 99));
		rooms[3].addItem(
				new Container("Goldbox", "a Gold box", false, 3, new Key("Door Key", "the Key to get out", true, 0)));
		rooms[3].addItem(new Key("Book", "A book with something can read", true, 99));
	}

	private static void setPlayer() {

		player.setPosition(0);
		player.setPlayerInv(new Inventory(3));
		player.addItem(new Key("Woodkey", "a wood key", true, 1));
	}

	// get input and change command base on mode
	public static void getInput(int input, JTextArea msg22, JTextArea msg3, JTextArea msg4) {
		switch (mode) {
		case "base":
			base(input, msg3, msg4);
			break;

		case "pick":
			playerPick(input, msg3, msg4);
			break;

		case "change":
			change(input, msg3, msg4);
			break;

		case "drop":
			playerDrop(input, msg3, msg4);
		}
	}

	// Change command base on input
	private static void base(int input, JTextArea msg3, JTextArea msg4) {
		switch (input) {
		case 1:
			msg3.setText(rooms[player.getPosition()].showRoom());
			String fb1 = "Pick: \n1-Cansel\n";
			for (int i = 0; i < rooms[player.getPosition()].getItems().length; i++) {
				if(rooms[player.getPosition()].getItems()[i]!=null) {
					fb1+=(i+2)+"-"+rooms[player.getPosition()].getItems()[i].getName()+"\n";
				}
			}
			msg4.setText(fb1);

			mode = "pick";
			break;

		case 2:
			// change only when npc in the room
			if (talk(msg3)) {
				String fb2 = "Swith item with: \n1-Cansel\n";
				int n2 = 1;
				for (Npc npc : npcs) {
					if (npc.getPosition() == player.getPosition()) {
						fb2 += (++n2) + "-" + npc.getName() + "\n";
					}
				}
				msg4.setText(fb2);

				mode = "change";
			}
			break;

		case 3:
			movePlayer(msg2, msg3);
			break;

		case 4:
			// change command only when Inventory has something
			if (showInv(msg3)) {
				String fb2 = "Drop: \n1-Cansel\n";
				for (int i = 0; i < player.getItems().length; i++) {
					if(player.getItems()[i]!=null) {
						fb2+=(i+2)+"-"+player.getItems()[i].getName()+"\n";
					}
				}
				msg4.setText(fb2);

				mode = "drop";
			}
			break;

		default:
			msg3.setText("Unavailable Command");
		}
	}

	private static void playerPick(int input, JTextArea msg3, JTextArea msg4) {
		if (input == 1) {
			mode = "base";
			msg4.setText("Commands\n1-Look around\n2-Talk\n3-Move to next room\n4-Check your item");
		} else if (input > 4) {
			msg3.setText("Unavailable Command");
		} else {
			for (int i = 0; i < rooms[player.getPosition()].getItems().length; i++) {
				if (i == input - 2) {
					if (rooms[player.getPosition()].getItems()[i] != null) {
						pickItem(rooms[player.getPosition()].getItems()[i], msg3);
					} else {
						msg3.setText("Unavailable Command");
					}
				}
			}
		}
		mode = "base";
		msg4.setText("Commands\n1-Look around\n2-Talk\n3-Move to next room\n4-Check your item");
	}

	private static void pickItem(GameObject gO, JTextArea msg3) {
		if (!gO.isMoveable()) {
			unlock(gO, msg3);
		} else {
			if (player.addItem(gO)) {
				rooms[player.getPosition()].removeItem(gO);
				msg3.setText("You put " + gO.getName() + " in your bag.");
			} else {
				msg3.setText("Fail to pick up item");
			}
		}
	}

	public static boolean talk(JTextArea msg3) {
		String fb = "";
		for (Npc npc : npcs) {
			if (npc.getPosition() == player.getPosition()) {
				fb += npc.getName() + " is here.\n";
				fb += npc.getPre() + "\n";
				GameObject item = npc.getNpvInv().getItems()[0];
				if (item != null) {
					fb += "he is holding " + item.getName();
				}
			}
		}
		if (fb.equals("")) {
			msg3.setText("No one is here");
			return false;
		} else {
			msg3.setText(fb);
			return true;
		}
	}

	private static void change(int input, JTextArea msg3, JTextArea msg4) {
		Npc[] toChange = new Npc[3];
		for (Npc npc : npcs) {
			if (npc.getPosition() == player.getPosition())
				for (int i = 0; i < toChange.length; i++) {
					if (toChange[i] == null) {
						toChange[i] = npc;
						break;
					}
				}
		}

		if (input == 1) {
			mode = "base";
			msg4.setText("Commands\n1-Look around\n2-Talk\n3-Move to next room\n4-Check your item");
		} else if (input > 4) {
			msg3.setText("Unavailable Command");
		} else {
			for (int i = 0; i < toChange.length; i++) {
				if (i == input - 2) {
					if (toChange[i] != null) {
						changeItem(toChange[i], msg3);
					} else {
						msg3.setText("Unavailable Command");
					}
				}
			}
		}
		mode = "base";
		msg4.setText("Commands\n1-Look around\n2-Talk\n3-Move to next room\n4-Check your item");
	}

	// switch the first item with npc, can not do it when you or npc has nothing
	private static void changeItem(Npc npc, JTextArea msg3) {
		if (npc.getItems()[0] == null) {
			msg3.setText(npc.getName() + " has nothing.");
		} else if (player.getItems()[0] == null) {
			msg3.setText("You have nothing.");
		} else {
			GameObject gO = npc.getItems()[0];
			npc.removeItem(gO);
			npc.addItem(player.getItems()[0]);
			player.removeItem(player.getItems()[0]);
			player.addItem(gO);

			msg3.setText("You switch item " + npc.getItems()[0].getName() + " and " + gO.getName() + " with "
					+ npc.getName());
		}

	}

	// unlock Container when you have the right key, can unlock when the room is
	// full.
	private static void unlock(GameObject gO, JTextArea msg3) {
		Container con = (Container) gO;
		if (con.getKeyU() == 0) {
			for (GameObject go : player.getItems()) {
				if (go != null) {
					Key k = (Key) go;
					if (con.getKeyU() == k.getId()) {
						con.setLocked(false);
					}
				}

			}
			if (!con.isLocked()) {
				end(msg3);
			} else {
				msg3.setText("You dont have key to open this door");
			}
		} else {
			if (con.isLocked()) {
				for (GameObject go : player.getItems()) {
					if (go != null) {
						Key k = (Key) go;
						if (con.getKeyU() == k.getId()) {
							con.setLocked(false);
						}
					}
				}
				if (!con.isLocked()) {
					if (rooms[player.getPosition()].addItem(con.getHoldItem())) {
						msg3.setText("You open " + con.getName() + "with a key.\n" + con.getHoldItem().getName()
								+ " is drap to floor.");
					} else {
						msg3.setText("You open " + con.getName() + "with a key,\nbut the room is full try next time.");
						con.setLocked(true);
					}
				} else {
					msg3.setText("You dont have key to open " + con.getName());
					con.setLocked(true);
				}

			} else {
				msg3.setText(con.getName() + " is open and empty.");
			}
		}
	}

	public static void movePlayer(JTextArea msg2, JTextArea msg3) {
		String fb2 = "";
		int newR = player.getPosition() + 1;
		if (newR == 4) {
			newR = 0;
		}
		player.setPosition(newR);

		fb2 += rooms[newR].getName() + "\n";
		for (Npc npc : npcs) {
			if (npc.getPosition() == newR) {
				fb2 += npc.getName() + " is here.";
			}
		}
		msg2.setText(fb2);
		msg3.setText("You move to next room.");
	}

	public static boolean showInv(JTextArea msg3) {
		String fb = "";
		for (GameObject go : player.getPlayerInv().getItems()) {
			if (go != null) {
				fb += go.getName() + "\n-" + go.getPre() + "\n";
			}
		}
		if (fb.equals("")) {
			msg3.setText("You have nothing.");
			return false;
		} else {
			msg3.setText(fb);
			return true;
		}
	}

	private static void playerDrop(int input, JTextArea msg3, JTextArea msg4) {
		if (input == 1) {
			mode = "base";
			msg4.setText("Commands\n1-Look around\n2-Talk\n3-Move to next room\n4-Check your item");
		} else if (input > 4) {
			msg3.setText("Unavailable Command");
		} else {
			for (int i = 0; i < player.getItems().length; i++) {
				if (i == input - 2) {
					if (player.getItems()[i] != null) {
						dropItem(player.getItems()[i], msg3);
					} else {
						msg3.setText("Unavailable Command");
					}
				}
			}
		}
		mode = "base";
		msg4.setText("Commands\n1-Look around\n2-Talk\n3-Move to next room\n4-Check your item");
	}

	// can not drop when the room is full.
	private static void dropItem(GameObject go, JTextArea msg3) {
		if (rooms[player.getPosition()].addItem(go)) {
			player.removeItem(go);
			msg3.setText("You drop " + go.getName() + " in " + rooms[player.getPosition()].getName());
		} else {
			msg3.setText(rooms[player.getPosition()].getName() + " is full.");
		}
	}

	// random move npc dose
	public static void moveNpc() {

		// change command to base when npc move
		mode = "base";
		msg4.setText("Commands\n1-Look around\n2-Talk\n3-Move to next room\n4-Check your item");

		for (Npc npc : npcs) {
			switch (getRandomInt(0, 3)) {
			case 0:
				move(npc);
				break;
			case 1:
				pick(npc);
				break;
			case 2:
				drop(npc);
				break;
			case 3:
				chat(npc);
				break;
			}
		}
	}

	private static void chat(Npc npc) {
		if (npc.getPosition() == player.getPosition()) {
			String fb = GameRun.msg2.getText();
			fb += "\n" + npc.getName() + ": " + npc.getLine();
			GameRun.msg2.setText(fb);
		}
	}

	private static void drop(Npc npc) {
		int p = npc.getPosition();
		GameObject toDrop = npc.getNpvInv().getItems()[0];
		if (toDrop != null) {
			if (rooms[p].addItem(toDrop)) {
				if (npc.removeItem(toDrop)) {
					if (npc.getPosition() == player.getPosition()) {
						String fb = msg2.getText();
						fb += "\n" + npc.getName() + " dorp " + toDrop.getName();
						msg2.setText(fb);
					}
				}
			}
		}
	}

	private static void pick(Npc npc) {
		int p = npc.getPosition();
		GameObject toPick = rooms[p].getRoomInv().getItems()[1];
		if (toPick != null) {
			if (npc.addItem(toPick)) {
				rooms[p].removeItem(toPick);
				if (npc.getPosition() == player.getPosition()) {
					String fb = msg2.getText();
					fb += "\n" + npc.getName() + " pick " + toPick.getName();
					msg2.setText(fb);
				}
			}
		}
	}

	private static void move(Npc npc) {
		if (npc.getPosition() == player.getPosition()) {
			String fb = msg2.getText();
			fb += "\n" + npc.getName() + " leve the room.";
			msg2.setText(fb);
		}

		int newR = npc.getPosition() + 1;
		if (newR == 4) {
			newR = 0;
		}
		npc.setPosition(newR);
		if (newR == player.getPosition()) {
			String fb = msg2.getText();
			fb += "\n" + npc.getName() + " is here.";
			msg2.setText(fb);
		}
	}

	private static int getRandomInt(int min, int max) {
		int re = (int) (Math.random() * max) + min;
		return re;
	}

	private static void end(JTextArea msg3) {
		msg3.setText("You unlock the door an get out.");
		GameRun.running = false;
	}

}
