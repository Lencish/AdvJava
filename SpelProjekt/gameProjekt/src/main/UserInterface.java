package main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

	private JFrame frame;

	public UserInterface() {
	}

	@Override
	public void run() {
		frame = new JFrame("Title");
		frame.setPreferredSize(new Dimension(1000, 400));

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		createComponents(frame.getContentPane());

		frame.pack();
		frame.setVisible(true);
	}

	private void createComponents(Container container) {
		// GridLayout gridLayout = new GridLayout(4, 1);
		GridLayout gridLayout = new GridLayout(2, 3, 10, 10);
		container.setLayout(gridLayout);

		JTextArea msg1 = new JTextArea("The Game\nUse item to get out the room.\nYou can only have 3 item.\nInput numbers for commands");
		JTextArea msg2 = new JTextArea("Start Room\nJason is here");
		GameRun.msg2 = msg2;
		JTextArea msg3 = new JTextArea("feed back");
		JTextArea msg4 = new JTextArea("Commands\n1-Look around\n2-Talk\n3-Move to next room\n4-Check your item");
		GameRun.msg4 = msg4;
		JTextField input = new JTextField("Input");

		JButton button = new JButton("Enter");

		MessageListener check = new MessageListener(msg2, msg3, msg4, input);
		button.addActionListener(check);

		container.add(msg1);
		container.add(msg2);
		container.add(msg3);
		container.add(msg4);
		container.add(input);
		container.add(button);

	}

	public JFrame getFrame() {
		return frame;
	}

}
