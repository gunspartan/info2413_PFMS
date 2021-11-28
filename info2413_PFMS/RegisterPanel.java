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
	public RegisterPanel(ContainerPanel parentPanel, CardLayout cl) {
		// Set size	
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		// Labels
		JLabel titleLabel = new JLabel("Register for PFMS");
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordReq1 = new JLabel("At least 8 characters");
		passwordReq1.setFont(passwordReq1.getFont().deriveFont(10.0f));
		JLabel passwordReq2 = new JLabel("At least one uppercase and lower case letter");
		passwordReq2.setFont(passwordReq2.getFont().deriveFont(10.0f));
		JLabel passwordReq3 = new JLabel("At least one number");
		passwordReq3.setFont(passwordReq3.getFont().deriveFont(10.0f));
		JLabel passwordReq4 = new JLabel("At least one special character (!, @, #, $, ?)");
		passwordReq4.setFont(passwordReq4.getFont().deriveFont(10.0f));
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
					err.printStackTrace();
					
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
		
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 3;
		add(passwordReq1, gc);
		gc.gridx = 0;
		gc.gridy = 4;
		add(passwordReq2, gc);
		gc.gridx = 0;
		gc.gridy = 5;
		add(passwordReq3, gc);
		gc.gridx = 0;
		gc.gridy = 6;
		add(passwordReq4, gc);
		
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy = 7;
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
		gc.gridy = 7;
		add(emailField, gc);
				
		// Button row
		gc.weighty = 5;
				
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.gridx = 0;
		gc.gridy = 8;
		add(registerBtn, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 8;
		add(backBtn, gc);
		
	}
}
