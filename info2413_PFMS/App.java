package info2413_PFMS;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


// Application GUI

// TODO Move GUI Components to Individual Classes
public class App {
	
	// Setup frame
	public JFrame frame;
	
	// Add Shopping List
	
	// Edit Shopping List
	
	// Add Grocery Item
	
	// Edit Grocery Item
	
	// Manage Categories

	// Notifications
	
	// Reports
	
	
	// Main
	public static void main(String s[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame;
				try {
					frame = new MainFrame("PFMS");
					frame.setSize(800, 600);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}

	
	// TODO Add Database Connection
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/info2413";
			String username = "root";
			String password = "Gu6570$$";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected");
			return conn;
		} catch (Exception e ) {
			System.out.println(e);
		}
		
		return null;
	}
	// Close Database Connection
	public static void closeQueitly(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {}
		}
		
	}
	public static void closeQueitly(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {}
		}
		
	}
	public static void closeQueitly(PreparedStatement stmnt) {
		if (stmnt != null) {
			try {
				stmnt.close();
			} catch (Exception e) {}
		}
		
	}
}
