package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessageListener implements ActionListener {

	private JTextArea msg2;
	private JTextArea msg3;
	private JTextArea msg4;
	private JTextField input;

	public MessageListener(JTextArea msg2, JTextArea msg3, JTextArea msg4, JTextField input) {
		super();
		this.msg2 = msg2;
		this.msg3 = msg3;
		this.msg4 = msg4;
		this.input = input;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = this.input.getText();

		int i = getInt(input);
		GameRun.getInput(i, msg2, msg3, msg4);

	}

	public static int getInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {

		}
		return 99;
	}
}
