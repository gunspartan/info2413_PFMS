package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPanel extends JPanel {
	private final ContainerPanel parentPanel;
	private final CardLayout cl;
	
	public RegisterPanel(ContainerPanel parentPanel, CardLayout cl) {
		this.parentPanel = parentPanel;
		this.cl = cl;
		
		// Set size	
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		// Labels
		JLabel titleLabel = new JLabel("Register for PFMS");
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		JLabel emailLabel = new JLabel("Email");

		// Input fields
		final JTextField usernameField = new JTextField(10);
		final JPasswordField passwordField = new JPasswordField(10);
		final JTextField emailField = new JTextField(10);
		
		// Register Button
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newUsername = usernameField.getText();
				String newPwd = passwordField.getText();
				String newEmail = emailField.getText();
				try {
					User.createUser(newUsername, newPwd, newEmail);	
					
				} catch (Exception err) {
					System.out.println(err);
					
				}
				
			}
			
		});
		
		// Back Button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleRegisterPanelBackBtn(e);
				
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
		
		gc.gridx = 0;
		gc.gridy = 3;
		add(emailLabel, gc);

		// Second Column
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		add(usernameField, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		add(passwordField, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		add(emailField, gc);
				
		// Button row
		gc.weighty = 5;
				
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.gridx = 0;
		gc.gridy = 4;
		add(registerBtn, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 4;
		add(backBtn, gc);
		
	}
}
