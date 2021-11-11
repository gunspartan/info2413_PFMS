package info2413_PFMS;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

enum PopupType{
	LOGIN_ERROR, CREATE_USER_SUCCESS, CREATE_USER_ERROR, NEW_INVENTORY, NEW_ITEM, EDIT_ITEM;
}

public class PopupFrame extends JFrame {
	private String title;
	private JFrame frame;
	private JPanel content;
	private JLabel contentLabel;
	private int sizeX, sizeY;
	public PopupFrame(PopupType type) {
		
		switch (type) {
		case LOGIN_ERROR:
			title = "Login Error";
			content = new JPanel();
			contentLabel = new JLabel("There was a login error.");
			content.add(contentLabel);
			sizeX = 400;
			sizeY = 150;
			break;
		case CREATE_USER_SUCCESS:
			title = "Account Created!";
			content = new JPanel();
			contentLabel = new JLabel("Your account has been created.");
			content.add(contentLabel);
			sizeX = 400;
			sizeY = 150;
			break;
		case CREATE_USER_ERROR:
			title = "Error Creating Account";
			content = new JPanel();
			contentLabel = new JLabel("There was an error creating an account.");
			content.add(contentLabel);
			sizeX = 400;
			sizeY = 150;
			break;
		default:
			break;
		
		}
		
		frame = new JFrame(title);
		frame.add(content);
		frame.setSize(sizeX, sizeY);
		frame.setVisible(true);
	}
}