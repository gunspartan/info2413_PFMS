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

public class EditItemPanel extends JPanel {
	private final ContainerPanel parentPanel;
	private final CardLayout cl;
	private int inventoryId;
	private int itemId;
	private String[] item;
	private String fileName;

	public EditItemPanel(ContainerPanel parentPanel, CardLayout cl,int inventoryId, int itemId) {
		this.parentPanel = parentPanel;
		this.cl = cl;
		this.inventoryId = inventoryId;
		this.itemId = itemId;
		item = GroceryItem.getGroceryItemById(itemId);

		// Set size 
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);

		// Labels
		JLabel titleLabel = new JLabel("Edit Item");
		JLabel imgLabel = new JLabel("Image:");
		JLabel nameLabel = new JLabel("Name:");
		JLabel categoryLabel = new JLabel("Category:");
		JLabel priceLabel = new JLabel("Price: $");
		JLabel expiryLabel = new JLabel("Expiry Date (YYYY-MM-DD):");
		JLabel qtyLabel = new JLabel("# Purchased:");
		JLabel numConsumedLabel = new JLabel("# Consumed");

		// File Browser
		fileName = item[0];
		JTextField fileNameField = new JTextField(10);
		fileNameField.setText(fileName);

		JFileChooser fc = new JFileChooser();

		JButton openBtn = new JButton("Open a File...");
		openBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int returnVal = fc.showOpenDialog(EditItemPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileName = fc.getSelectedFile().toString();
					fileName = fileName.replace("\\", "/");
					System.out.println(fileName);
					fileNameField.setText(fc.getSelectedFile().toString());
				}
			}

		});

		// Fields
		JTextField nameField = new JTextField(10);
		nameField.setText(item[1]);
		JTextField categoryField = new JTextField(10);
		categoryField.setText(item[2]);
		JTextField priceField = new JTextField(10);
		priceField.setText(item[3]);
		JTextField expiryField = new JTextField(10);
		expiryField.setText(item[4]);
		JTextField qtyField = new JTextField(10);
		qtyField.setText(item[5]);
		JTextField numConsumedField = new JTextField(10);
		numConsumedField.setText(item[6]);

		// Update Button
		JButton createBtn = new JButton("Update");
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
				GroceryItem.updateItem(itemId, img, name, category, price, expiry, qty, numConsumed);

				// Update spending
				GroceryInventory.updateTotalSpending(inventoryId);
				Category.updateTotalSpending(category);
			}
		});

		// Delete Button
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GroceryItem.deleteItem(itemId);
				// Update spending
				GroceryInventory.updateTotalSpending(inventoryId);
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

		// Delete Button
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 3;
		gc.gridy = 4;
		add(deleteBtn, gc);

		// Create Button
		gc.gridx = 4;
		gc.gridy = 4;
		add(createBtn, gc);
	}
}
