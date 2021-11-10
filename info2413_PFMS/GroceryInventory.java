package info2413_PFMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GroceryInventory {
	// GroceryInventory has a user
	User user;
	float totalSpent;
	String shoppingDate;
	// GroceryInventory has a list of items
	ArrayList<GroceryItem> items = new ArrayList<GroceryItem>();
	
	// Constructor
	GroceryInventory(User user, String shoppingDate) {
		this.user = user;
		this.shoppingDate = shoppingDate;
	}
	
	// Setters and getters
	public void setUser(User newUser) {
		user = newUser;
		// Make change to database
	}
	
	public void setShoppingDate(String newShoppingDate) {
		shoppingDate = newShoppingDate;
		// Make change to database
	}
	
	public User getUser() {
		return user;
	}
	
	public String getShoppingDate() {
		return shoppingDate;
	}
	
	// createGroceryInventory
	// Create GroceryInventory in database
	
	// deleteGroceryInventory
	// Delete GroceryInventory in database
	
	// addGroceryItem
	// Add item to Inventory
	public void addGroceryItem(GroceryItem newItem) {
		items.add(newItem);
		// Make change to database
	}
	
	// deleteGroceryItem
	// Delete item from Inventory
	public void deleteGroceryItem(GroceryItem newItem) {
		items.remove(newItem);
		// Make change to database
	}
	
	// checkExpiry
	// Check expiry dates of all foods
	public ArrayList<GroceryItem> checkExpiry() {
		ArrayList<GroceryItem> expiringItems = new ArrayList<GroceryItem>();
		for (int i = items.size() - 1; i >= 0; i--) {
			// If expiring
			// Add to expiringItems
		}
		
		return expiringItems;
	}
	
	// calculateSpending
	// Check total spending on all foods
	
	
	// -- SQL Queries
	// Get Inventories
	public static ArrayList<String[]> getGroceryInventories(User currUser) {
		if (currUser == null) {
			return null;
		}
		
		int userId = currUser.getUserId();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT GroceryInventory.GroceryInventoryId, GroceryInventory.ShopDate, GroceryInventory.TotalSpent "
					+ "FROM UserInfo INNER JOIN GroceryInventory "
					+ "WHERE UserInfo.UserId = " + userId + " AND UserInfo.UserId = GroceryInventory.UserId;");
			rs = stmt.executeQuery();
			
			ArrayList<String[]> array = new ArrayList<>();
			while (rs.next()) {
				String[] groceryInventory = new String[3];
				groceryInventory[0] = rs.getString("GroceryInventoryId");
				groceryInventory[1] = rs.getString("ShopDate");
				groceryInventory[2] = rs.getString("TotalSpent");
				array.add(groceryInventory);
			}
			System.out.println("All records have been selected");
			return array;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return null;
	}
	
	public static String[] getGroceryInventoryById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM GroceryInventory "
					+ "WHERE GroceryInventoryId = " + id + ";");
			rs = stmt.executeQuery();
			String[] groceryInventory = new String[3];
			while (rs.next()) {
				groceryInventory[0] = rs.getString("GroceryInventoryId");
				groceryInventory[1] = rs.getString("ShopDate");
				groceryInventory[2] = rs.getString("TotalSpent");
			}
			System.out.println("All records have been selected");
			return groceryInventory;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return null;
	}
}
