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

public class EditCategoriesPanel extends JPanel {
	public EditCategoriesPanel(ContainerPanel parentPanel, CardLayout cl, int categoryId) {
		
		// Set size
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		// Labels 
		JLabel titleLabel = new JLabel("Edit Category");
		JLabel categoryLabel = new JLabel("Category: ");
		
		// Text field
		JTextField categoryField = new JTextField(10);
		categoryField.setText(Category.getCategoryById(categoryId));
		
		// Submit button
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String categoryName = categoryField.getText();
				Category.updateCategory(categoryId, categoryName);
			}	
		});
		
		// Delete button
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Category.deleteCategory(categoryId);
			}
		});
		
		// Back button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleEditCategoriesPanelBackBtn(e);
			}
		});
		
		// Layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
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
		add(categoryLabel, gc);
		gc.gridx = 1;
		add(categoryField, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		add(deleteBtn, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		add(submitBtn, gc);
	}
}
