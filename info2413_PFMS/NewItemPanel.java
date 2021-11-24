package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewItemPanel extends JPanel {
	private String fileName;
	
	public NewItemPanel(ContainerPanel parentPanel, CardLayout cl, int inventoryId) {
		// Set size 
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		// Labels
		JLabel titleLabel = new JLabel("New Item");
		JLabel imgLabel = new JLabel("Image:");
		JLabel nameLabel = new JLabel("Name:");
		JLabel categoryLabel = new JLabel("Category:");
		JLabel priceLabel = new JLabel("Price: $");
		JLabel expiryLabel = new JLabel("Expiry Date (YYYY-MM-DD):");
		JLabel qtyLabel = new JLabel("# Purchased:");
		JLabel numConsumedLabel = new JLabel("# Consumed");
		
		// File Browser
		JTextField fileNameField = new JTextField(10);
		
		JFileChooser fc = new JFileChooser();
		
		JButton openBtn = new JButton("Open a File...");
		openBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int returnVal = fc.showOpenDialog(NewItemPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileName = fc.getSelectedFile().toString();
					fileName = fileName.replace("\\", "/");
					System.out.println(fileName);
					fileNameField.setText(fc.getSelectedFile().toString());
				} else {
					fileNameField.setText("");
				}
			}
			
		});
		
		// Fields
		JTextField nameField = new JTextField(10);
		JTextField categoryField = new JTextField(10);
		JTextField priceField = new JTextField(10);
		JTextField expiryField = new JTextField(10);
		JTextField qtyField = new JTextField(10);
		JTextField numConsumedField = new JTextField(10);
		numConsumedField.setText("0");
		
		// Create Button
		JButton createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String img = fileName;
				String name = nameField.getText();
				String category = categoryField.getText();
				float price = Float.parseFloat(priceField.getText());
				String expiry = expiryField.getText();
				int qty = Integer.parseInt(qtyField.getText());
				int numConsumed = Integer.parseInt(numConsumedField.getText());
				GroceryItem.createItem(inventoryId, img, name, category, price, expiry, qty, numConsumed);
				
				// Update spending
				GroceryInventory.updateTotalSpending(inventoryId);
				Category.updateTotalSpending(category);
			}
		});
		
		// Back Button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleNewItemPanelBackBtn(e, inventoryId);
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
		
		// Add Back Button
		gc.gridx = 3;
		gc.gridy = 0;
		add(backBtn, gc);
		
		// Labels
		gc.ipadx = 10;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 0;
		gc.gridy = 1;
		add(imgLabel, gc);
		
		gc.gridx = 3;
		gc.gridy = 1;
		add(nameLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		add(categoryLabel, gc);
		
		gc.gridx = 3;
		gc.gridy = 2;
		add(priceLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		add(expiryLabel, gc);
		
		gc.gridx = 3;
		gc.gridy = 3;
		add(qtyLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		add(numConsumedLabel, gc);
		
		// Fields
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		add(fileNameField, gc);
		gc.gridx = 2;
		gc.gridy = 1;
		add(openBtn, gc);
		
		gc.gridx = 4;
		gc.gridy = 1;
		add(nameField, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(categoryField, gc);
		
		gc.gridx = 4;
		gc.gridy = 2;
		add(priceField, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		add(expiryField, gc);
		
		gc.gridx = 4;
		gc.gridy = 3;
		add(qtyField, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		add(numConsumedField, gc);
		
		// Create Button
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 4;
		gc.gridy = 4;
		add(createBtn, gc);
	}
}
