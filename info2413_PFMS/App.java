package info2413_PFMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


// Application GUI

// TODO Move GUI Components to Individual Classes
public class App {
	private final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private final static Date dateToday = new Date();
	private final static String today = df.format(dateToday);
	
	// Test for 1st day of month
//	private final static Date dateTest1 = new Date(2021, 11, 01);
//	private final static String dateTest = df.format(dateTest1);

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
	
	public static String getToday() {
		return today;
	}
	
	public static boolean isFirstDay(String date) {
		return Pattern.matches("........01", date);
	}
	
	public static long daysDiff(String expiryDate) {
		Date expiry;
		long diffDays = 0;
		try {
			expiry = df.parse(expiryDate);
			Date dateToday = df.parse(today);
			long diffMillis = expiry.getTime() - dateToday.getTime();
			diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diffDays;
	}
}
