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
	// Main
	public static void main(String s[]) {
		App app = new App();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame;
				try {
					frame = new MainFrame("PFMS");
					frame.setSize(800, 600);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					
					// Test update spending function
					GroceryInventory.updateTotalSpending(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
	// Connect to database
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/info2413";
			// ------ CREATE A USER IN YOUR LOCAL DATABASE WITH THESE COMMANDS -------
			// CREATE USER 'info2413'@'localhost' identified by 'PFMS_ADMIN';
			// GRANT ALL PRIVILEGES ON info2413.* TO 'info2413'@'localhost';
			// FLUSH PRIVILEGES;
			String username = "info2413";
			String password = "PFMS_ADMIN";
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
