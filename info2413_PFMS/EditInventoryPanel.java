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

public class EditInventoryPanel extends JPanel {
	public EditInventoryPanel(ContainerPanel parentPanel, CardLayout cl, int inventoryId) {
		String[] groceryInventory = GroceryInventory.getGroceryInventoryById(inventoryId);
		// Set size	
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		// Labels
		JLabel titleLabel = new JLabel("Edit Inventory");
		JLabel shopDateLabel = new JLabel("Shopping Date: ");
		String shopDate = groceryInventory[1];
		
		// Text Field
		JTextField shopDateField = new JTextField(10);
		shopDateField.setText(shopDate);
		
		// Back button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleEditInventoryPanelBackBtn(e);
			}
		});
		
		// Submit button
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newShopDate = shopDateField.getText();
				GroceryInventory.updateInventory(inventoryId, newShopDate);
			}
			
		});
		
		// Delete button
		JButton deleteInventoryBtn = new JButton("Delete Inventory");
		deleteInventoryBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GroceryInventory.deleteInventory(inventoryId);
				// Return to home page after deletion
				parentPanel.handleInventoryPanelHomeBtn(e);
			}
			
		});
		
		// Layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0.75;
		gc.weighty = 0.25;
		add(titleLabel, gc);

		gc.gridx = 1;
		add(backBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.weightx = 0.75;
		gc.weighty = 0.75;
		gc.gridx = 0;
		gc.gridy = 1;
		add(shopDateLabel, gc);
		gc.gridx = 1;
		add(shopDateField, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		add(deleteInventoryBtn, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		add(submitBtn, gc);
	}
}
