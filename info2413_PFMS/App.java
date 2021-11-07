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
	
	// Temp Vars
	User currUser;
	// Temp user inventories
	LinkedList<String> temp = new LinkedList<>();
	
	public static ArrayList<User> users = new ArrayList<User>();
	
	// Setup frame
	public JFrame frame;
	public JButton goHomeBtn;
	
	// Setup Login
	public JPanel loginPage;
	public JLabel loginTitle;
	public JLabel loginUsername;
	public JTextField loginUserText;
	public JLabel loginPwd;
	public JPasswordField loginPwdText;
	public JButton loginBtn;
	public JButton loginRegisterBtn;
	
	// Setup Register
	public JPanel registerPage;
	public JLabel registerTitle;
	public JLabel registerUsername;
	public JTextField registerUserText;
	public JLabel registerPwd;
	public JPasswordField registerPwdText;
	public JLabel registerEmail;
	public JTextField registerEmailText;
	public JButton registerBtn;
	
	// Setup Home
	public JPanel homePage;
	public JLabel homeTitle;
	public JLabel homeLabel;
	
	// Setup Inventory Page
	public JPanel inventoryPage;
	public JLabel inventoryTitle;
	public JLabel inventoryLabel;
	public JTable inventoryTable;
	public JButton inventoryEdit;

	public void setup() {
		frame = new JFrame("PFMS");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Global Go Home button
		goHomeBtn = new JButton(new goHomeAction("Home"));
		
		// Temporary Inventories
		temp.add("Oct 27, 2021");
		temp.add("Oct 26, 2021");
		temp.add("Oct 25, 2021");
		temp.add("Oct 24, 2021");
		temp.add("Oct 23, 2021");
		temp.add("Oct 22, 2021");
		
		frame.setVisible(true);	
	}
	
	// Login
	public JPanel loginPage() {
		loginPage = new JPanel();
		loginPage.setLayout(null);
		
		// Page title
		loginTitle = new JLabel("Welcome to PFMS");
		loginTitle.setBounds(80, 50, 500, 28);
		
		// Username
		loginUsername = new JLabel("Username");
		loginUsername.setBounds(80, 100, 100, 28);
		loginUserText = new JTextField();
		loginUserText.setBounds(150, 100, 150, 28);
		
		// Password
		loginPwd = new JLabel("Password");
		loginPwd.setBounds(80, 140, 100, 28);
		loginPwdText = new JPasswordField();
		loginPwdText.setBounds(150, 140, 150, 28);
		
		// Login Button
		loginBtn = new JButton(new loginAction("Login"));
		loginBtn.setBounds(80, 200, 100, 35);
		
		// Register Button
		loginRegisterBtn = new JButton(new loginRegisterAction("Register"));
		loginRegisterBtn.setBounds(200, 200, 100, 35);
		
		// Add elements to panel
		loginPage.add(loginTitle);
		loginPage.add(loginUsername);
		loginPage.add(loginUserText);
		loginPage.add(loginPwd);
		loginPage.add(loginPwdText);
		loginPage.add(loginBtn);
		loginPage.add(loginRegisterBtn);
		
		return loginPage;
	}
	
	// Register
	public JPanel registerPage() {
		registerPage = new JPanel();
		registerPage.setLayout(null);
		
		// Page title
		registerTitle = new JLabel("Register");
		registerTitle.setBounds(80, 50, 100, 28);
		
		// Username
		registerUsername = new JLabel("Username");
		registerUsername.setBounds(80, 100, 100, 28);
		registerUserText = new JTextField();
		registerUserText.setBounds(150, 100, 150, 28);
		
		// Password
		registerPwd = new JLabel("Password");
		registerPwd.setBounds(80, 140, 100, 28);
		registerPwdText = new JPasswordField();
		registerPwdText.setBounds(150, 140, 150, 28);
		
		// Email
		registerEmail = new JLabel("Email");
		registerEmail.setBounds(80, 180, 100, 28);
		registerEmailText = new JTextField();
		registerEmailText.setBounds(150, 180, 150, 28);
		
		// Register Button
		registerBtn = new JButton("Register");
		registerBtn.setBounds(80, 240, 100, 35);
		
		// Add elements to panel
		registerPage.add(registerTitle);
		registerPage.add(registerUsername);
		registerPage.add(registerUserText);
		registerPage.add(registerPwd);
		registerPage.add(registerPwdText);
		registerPage.add(registerEmail);
		registerPage.add(registerEmailText);
		registerPage.add(registerBtn);
		
		return registerPage;
	}
	
	// Home
	public JPanel homePage() {
		homePage = new JPanel();
		homePage.setLayout(null);
		
		// Title
		homeTitle = new JLabel("Home");
		homeTitle.setBounds(80, 50, 100, 28);
		
		// Label for grocery inventory
		homeLabel = new JLabel("My Inventories:");
		homeLabel.setBounds(80, 120, 100, 28);
		
		// List of Grocery Inventory

		// Get inventories from User
		//for (int i = currUser.getInventory().size() - 1; i >= 0; i--) {
			//JLabel inventory = new JLabel(currUser.getInventory().get(i).getShoppingDate());
			//inventory.setBounds(80, 100, 600, 40);
			//homePage.add(inventory);
		//}
		
		
		// Sample Inventory
		for (int i = temp.size() - 1; i >= 0; i--) {
			JButton inventoryBtn = new JButton(new selectInventoryAction(temp.get(i)));
			inventoryBtn.setBounds(80, 150 + (45 * i) , 600, 40);
			homePage.add(inventoryBtn);
		}
		
		// Add elements to panel
		homePage.add(homeTitle);
		homePage.add(homeLabel);
		
		return homePage;
	}
	
	// Inventory View Page
	public JPanel inventoryPage(String currInventory) { // Temp
		inventoryPage = new JPanel();
		inventoryPage.setLayout(null);
		
		// Title
		inventoryTitle = new JLabel("My Grocery Inventory");
		inventoryTitle.setBounds(80, 50, 200, 28);
		
		// Label for current inventory
		inventoryLabel = new JLabel(currInventory);
		inventoryLabel.setBounds(80, 120,  100, 28);
		
		// Table of items
		// Sample items
		String[] columnNames = {"Image", "Name", "Quantity", "Price", "Category", "Purchase Date", "Expiry Date"};
		ImageIcon testImg = new ImageIcon("/img/apple-pink-lady.png");
		Object[][] data = {
				{testImg, "Apple", new Integer(5), new Float(0.99), "Fruit", "10-27-21", "10-31-21"},
				{testImg, "Banana", new Integer(5), new Float(1.99), "Fruit", "10-27-21", "10-31-21"}
				};
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		// Create Table
		inventoryTable = new JTable(model) {
			// Does not work
			public Class getColumnClass(int column) {
				return (column == 0) ? Icon.class : Object.class;
			}
		};
		
		// Resize row
		inventoryTable.setRowHeight(60);
		
		// Set so users cannot edit the table directly (Subject to change)
		inventoryTable.setDefaultEditor(Object.class, null);
		inventoryTable.setBounds(80, 150, 600, 500);
		JScrollPane scrollPane = new JScrollPane(inventoryTable);
		inventoryTable.setFillsViewportHeight(true);
		scrollPane.setBounds(80, 150, 600, 500);
		// TODO Add Edit Button
		
		// Go Home Button
		goHomeBtn.setBounds(600, 50, 100, 28);
		
		// Add Elements
		inventoryPage.add(inventoryTitle);
		inventoryPage.add(inventoryLabel);
		inventoryPage.add(goHomeBtn);
		inventoryPage.add(scrollPane);
		
		return inventoryPage;
	}
	
	// Add Shopping List
	
	// Edit Shopping List
	
	// Add Grocery Item
	
	// Edit Grocery Item
	
	// Manage Categories

	// Notifications
	
	// Reports
	
	// Change Panel
	public void changePanel(JPanel newPanel) {
		frame.getContentPane().removeAll();
		frame.add(newPanel);
		frame.revalidate();
		frame.repaint();
	}
	
	// Main
	public static void main(String s[]) throws Exception {
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
//		App app = new App();
//		app.setup();
//		app.loginPage();
//		app.registerPage();
//		app.homePage();
//		app.changePanel(app.loginPage);
//		
//		// Create users
//		ArrayList<String[]> userInfo = User.getUserInfo();
//		
//		// Create all users
//		for (int i = 0; i < userInfo.size(); i++) {
//			int userId = Integer.parseInt(userInfo.get(i)[0]);
//			String username = userInfo.get(i)[1];
//			String userPwd = userInfo.get(i)[2];
//			String email = userInfo.get(i)[3];
//			float budget = Float.parseFloat(userInfo.get(i)[4]);
//			
//			users.add(new User(userId, username, userPwd, email, budget));
//		}

		
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
	
	// ---- Action Listeners ----
	// Back to home page
	class goHomeAction extends AbstractAction {
		public goHomeAction(String text) {
			super(text);
		}
		public void actionPerformed(ActionEvent e) {
			changePanel(homePage);
		}
	}
	
	// Login Action
	class loginAction extends AbstractAction {
		public loginAction(String text) {
			super(text);
		}
		public void actionPerformed(ActionEvent e) {
			// Authenticate user
			boolean authenticated = false;
			int i = 0;
			while (i < users.size() && !authenticated) {
				if (loginUserText.getText().equals(users.get(i).getUsername()) && loginPwdText.getText().equals(users.get(i).getPassword())) {
					System.out.println("Authorized User");
					changePanel(homePage);
					authenticated = true;
				}
				i++;
			}
			if (!authenticated) {
				System.out.println("Unauthorized User");
			}
		}
	}
	
	// User clicks on register button on login page
	class loginRegisterAction extends AbstractAction {
		public loginRegisterAction(String text) {
			super(text);
		}
		public void actionPerformed(ActionEvent e) {
			changePanel(registerPage);
		}
	}
	
	// Select inventory
	class selectInventoryAction extends AbstractAction {
		public selectInventoryAction(String text) {
			super(text);
		}
		public void actionPerformed(ActionEvent e) {
			JButton btnPressed = (JButton) e.getSource();
			// Temporary
			changePanel(inventoryPage(btnPressed.getText()));
			
		}
	}
}
