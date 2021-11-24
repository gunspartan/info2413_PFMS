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

public class App {
	private final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private final static Date dateToday = new Date();
	private final static String today = df.format(dateToday);

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
				} catch (Exception e) {
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
			String username = "info2413";
			String password = "PFMS_ADMIN";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Close Database Connection
	public static void closeQueitly(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public static void closeQueitly(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void closeQueitly(PreparedStatement stmnt) {
		if (stmnt != null) {
			try {
				stmnt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// Current Date
	public static String getToday() {
		return today;
	}
	
	// Check for 1st day of the month
	public static boolean isFirstDay(String date) {
		return Pattern.matches("........01", date);
	}
	
	// Returns day difference
	public static long daysDiff(String expiryDate) {
		Date expiry;
		long diffDays = 0;
		try {
			expiry = df.parse(expiryDate);
			Date dateToday = df.parse(today);
			long diffMillis = expiry.getTime() - dateToday.getTime();
			diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diffDays;
	}
}
