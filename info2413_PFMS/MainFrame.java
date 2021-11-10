package info2413_PFMS;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	private ContainerPanel containerPanel;
	
	public MainFrame(String text) throws Exception {
		super(text);
		// Set layout manager
		setLayout(new BorderLayout());
		// Create Swing component
		containerPanel = new ContainerPanel();
		// Add Swing components to content
		add(containerPanel);
	}
}
	