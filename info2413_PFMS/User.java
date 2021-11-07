package info2413_PFMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class User {
	int userId;
	String username;
	String password;
	String email;
	float budget;
	
	// User can have multiple grocery inventories
	LinkedList<GroceryInventory> inventory = new LinkedList<GroceryInventory>();
	
	// Constructor
	User(int userId, String username, String password, String email, float budget) throws Exception {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.budget = budget;
	}
	
	// Setters and getters
	
	// Maybe don't need setters
	public void setUsername(String newUsername){
		username = newUsername;
		// Make change in databaase
	}
	
	public void setPassword(String newPwd) {
		password = newPwd;
		// Make change in database
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
		// Make change in database
	}
	
	public void setBudget(float newBudget) {
		budget = newBudget;
		// Make change in database
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public float getBudget() {
		return budget;
	}
	
	public LinkedList<GroceryInventory> getInventory() {
		return inventory;
	}
	
	// Create User
	// Add user to database
	public static void createUser(String username, String userPwd, String email) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO UserInfo (Username, UserPwd, Email)" +
					"VALUES ('" + username + "','" + userPwd + "','" + email + "');");
			stmt.executeUpdate();
			
			JFrame successFrame = new JFrame("Account Created");
			JLabel successMessage = new JLabel("Your account has been created.");
			successFrame.add(successMessage);
			successFrame.setSize(200, 150);
			successFrame.setVisible(true);
		} catch (Exception e) {
			System.out.println(e);
			JFrame errorFrame = new JFrame("Error Creating Account");
			JLabel errorMessage = new JLabel("An Error Occurred.");
			errorFrame.add(errorMessage);
			errorFrame.setSize(200, 150);
			errorFrame.setVisible(true);
		} finally {
			System.out.println("Insert completed");
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}
	
	// Login
	// Check database for username and corresponding password
	
	// updateUser
	// Update user information to database
	
	// createBudget
	// Add budget to the database
	
	// updateBudget
	// Update budget to the database
	
	// checkBudget
	// Send notification if budget is low
	
	// Manage inventories
	public void addGroceryInventory(GroceryInventory newInventory) {
		inventory.add(newInventory);
	}
	
	public void deleteGroceryInventory(GroceryInventory newInventory) {
		inventory.remove(newInventory);
	}
	
	public static ArrayList<String[]> getUserInfo() throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM UserInfo");
			
			res = stmt.executeQuery();
			
			ArrayList<String[]> array = new ArrayList<String[]>();
			while (res.next()) {
				String[] userInfo = new String[5];
				userInfo[0] = res.getString("UserId");
				userInfo[1] = res.getString("Username");
				userInfo[2] = res.getString("UserPwd");
				userInfo[3] = res.getString("Email");
				userInfo[4] = res.getString("Budget");
				
				array.add(userInfo);
			}
			System.out.println("All records have been selected");
			return array;
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			App.closeQueitly(res);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return null;
	}
}
