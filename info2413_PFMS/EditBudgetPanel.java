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

public class EditBudgetPanel extends JPanel {
	public EditBudgetPanel (ContainerPanel parentPanel, CardLayout cl, User usr) {
		// Set size 
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);

		// Labels
		JLabel titleLabel = new JLabel("Edit Budget");
		JLabel budgetLabel = new JLabel("Budget: ");
		
		// Text field
		JTextField budgetField = new JTextField(10);
		String currBudget = Float.toString(usr.getBudget(usr.getUserId()));
		budgetField.setText(currBudget);
		
		// Back button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.handleEditBudgetPanelBackBtn(e);
			}
		});
		
		// Submit button
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User.setBudget(usr, budgetField.getText());
			}
		});
		
		// Layout
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.weightx = 0.25;
		gc.weighty = 0.25;
		
		// Add Labels
		add(titleLabel, gc);
		
		// Back Button
		gc.gridx = 2;
		add(backBtn, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(budgetLabel, gc);
		gc.gridx = 1;
		add(budgetField, gc);
		gc.gridx = 2;
		add(submitBtn, gc);
	}
}
