package info2413_PFMS;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CategoriesPanel extends JPanel {
	private final JScrollPane scrollPane;
	private final JPanel innerPanel;
	private ArrayList<String[]> categories;
	public CategoriesPanel(ContainerPanel parentPanel, CardLayout cl) {
		// Set size
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		// Initialize scroll pane
		scrollPane = new JScrollPane();
		innerPanel = new JPanel();
		
		// Labels
		JLabel titleLabel = new JLabel("Manage Categories");
		JLabel categoryLabel = new JLabel("Category Name: ");
		JLabel totalSpentLabel = new JLabel("Total Spent: ");
		
		// New Category Button
		JButton newCategoryBtn = new JButton("Create new category");
		newCategoryBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleCategoriesPanelNewCategoryBtn(e);
			}
		});
		
		// Back Button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleCategoriesPanelBackBtn(e);
			}
			
		});
		
		// Layout
		setLayout(new GridBagLayout());
		innerPanel.setLayout(new GridBagLayout());
		
		// Headings layout
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.25;
		gc.weighty = 0.25;
		
		// List Layout
		GridBagConstraints listConstraints = new GridBagConstraints();
		listConstraints.anchor = GridBagConstraints.PAGE_START;
		listConstraints.weightx = 0.25;
		listConstraints.weighty = 0.15;
		
		// Add Title 
		add(titleLabel, gc);

		
		
		// Add Back Button
		gc.gridx = 1;
		gc.gridy = 0;
		add(backBtn, gc);
		

		
		// Add Labels
		listConstraints.gridx = 0;
		listConstraints.gridy = 0;
		innerPanel.add(categoryLabel, listConstraints);
		listConstraints.gridx = 1;
		listConstraints.gridy = 0;
		innerPanel.add(totalSpentLabel, listConstraints);
		
		// New Category Button
		listConstraints.gridx = 2;
		listConstraints.gridy = 0;
		innerPanel.add(newCategoryBtn, listConstraints);
		
		categories = Category.getCategories();
		if (categories != null || !categories.isEmpty()) {
			for (int i = 0; i < categories.size(); i ++) {
				int categoryId = Integer.parseInt(categories.get(i)[0]);
				JLabel categoryName = new JLabel(categories.get(i)[1]);
				String formatted = String.format("%.2f", Float.parseFloat(categories.get(i)[2]));
				JLabel totalSpent = new JLabel (formatted);
				JButton editBtn = new JButton("EDIT");
				editBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						parentPanel.handleCategoriesPanelEditBtn(e, categoryId);
					}
				});
				
				// Add Labels
				listConstraints.gridx = 0;
				listConstraints.gridy = i + 1;
				innerPanel.add(categoryName, listConstraints);
				listConstraints.gridx = 1;
				listConstraints.gridy = i + 1;
				innerPanel.add(totalSpent, listConstraints);
				listConstraints.gridx = 2;
				listConstraints.gridy = i + 1;
				innerPanel.add(editBtn, listConstraints);
			}
		} else {
			JLabel emptyCategoryLabel = new JLabel("You have no categories");
			listConstraints.gridx = 0;
			listConstraints.gridy = 1;
			innerPanel.add(emptyCategoryLabel, listConstraints);
		}
		
		// Layout for scroll pane
		gc.anchor = GridBagConstraints.PAGE_START;
		scrollPane.add(innerPanel);
		scrollPane.setViewportView(innerPanel);
		gc.ipadx = 750;
		gc.ipady = 450;
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 1;
		add(scrollPane, gc);
	}
}
