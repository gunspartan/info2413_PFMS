package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 6054267968959897295L;
	private final ContainerPanel parentPanel;
	private final CardLayout cl;

	public LoginPanel(ContainerPanel parentPanel, CardLayout cl) {
		this.parentPanel = parentPanel;
		this.cl = cl;

		// Set size
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);

		// Labels
		JLabel titleLabel = new JLabel("Welcome to PFMS");
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");

		// Input fields
		final JTextField usernameField = new JTextField(10);
		final JPasswordField passwordField = new JPasswordField(10);

		// Login Button
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();

				// Authentication				
				if ("test".equals(username) && "test".equals(password)) {
					parentPanel.handleLoginPanelLoginBtn(e);
				}
			}

		});

		// Register Button
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleLoginPanelRegisterBtn(e);
			}

		});

		// Layout
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// Add title
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.25;
		gc.weighty = 0.25;
		add(titleLabel, gc);

		// First column
		gc.gridx = 0;
		gc.gridy = 1;

		add(usernameLabel, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		add(passwordLabel, gc);

		// Second Column
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		add(usernameField, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		add(passwordField, gc);

		// Button row
		gc.weighty = 5;

		gc.anchor = GridBagConstraints.PAGE_START;
		gc.gridx = 0;
		gc.gridy = 3;
		add(loginBtn, gc);

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 3;
		add(registerBtn, gc);

	}
}
