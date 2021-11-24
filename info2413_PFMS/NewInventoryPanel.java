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
import javax.swing.JTextField;

public class NewInventoryPanel extends JPanel {
	private int userId;
	
	public NewInventoryPanel(ContainerPanel parentPanel, CardLayout cl, User usr) {
		userId = usr.getUserId();
		// Set size
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		// Labels
		JLabel titleLabel = new JLabel("Create a new inventory");
		JLabel shopDateLabel = new JLabel("Shopping Date (YYYY-MM-DD): ");
		
		// Input Fields
		JTextField shopDateField = new JTextField(10);
		
		// Back Button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleNewInventoryPanelBackBtn(e);
			}
			
		});
		
		// Create Button
		JButton createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String shopDate = shopDateField.getText();
				GroceryInventory.createGroceryInventory(usr, shopDate);
				
			}
			
		});
		
		// Layout
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		// Add title
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.75;
		gc.weighty = 0.25;
		add(titleLabel, gc);
		
		// Add Back button
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 0;
		add(backBtn, gc);
		
		// First Column
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.weightx = 0.75;
		gc.weighty = 0.75;
		gc.gridx = 0;
		gc.gridy = 1;
		add(shopDateLabel, gc);
		
		// Second Column
		gc.gridx = 1;
		gc.gridy = 1;
		add(shopDateField, gc);
		
		// Third Column
		gc.gridx = 2;
		gc.gridy = 1;
		add(createBtn, gc);
		
		
	}
}
