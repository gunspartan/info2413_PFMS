package info2413_PFMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GroceryInventory {
	// -- SQL Queries -- 
	// createGroceryInventory
	public static void createGroceryInventory(User user, String shopDate) {
		int userId = user.getUserId();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO GroceryInventory (UserId, ShopDate) "
					+ "VALUES (" + userId + ", '" + shopDate + "');");
			stmt.executeUpdate();
			
			System.out.println("Success");
		} catch (Exception e) {
			e.printStackTrace();
			// Error popup
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}


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
			return array;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return null;
	}
	
	// Get Inventory By Id
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
			return groceryInventory;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return null;
	}
	
	// Update Spending
	public static void updateTotalSpending(int id) {
		float totalSpent = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT ROUND(SUM(GroceryItem.Price * GroceryItem.Qty), 2) AS TotalSpent "
					+ "FROM GroceryItem INNER JOIN GroceryInventory "
					+ "WHERE GroceryItem.GroceryInventoryId = GroceryInventory.GroceryInventoryId AND GroceryInventory.GroceryInventoryId = " + id + ";");
			rs = stmt.executeQuery();
			while(rs.next()) {
				totalSpent = rs.getFloat("TotalSpent");
			}
			
			stmt1 = conn.prepareStatement("UPDATE GroceryInventory "
					+ "SET TotalSpent = " + totalSpent + "WHERE GroceryInventoryId = " + id + ";");
			stmt1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(stmt1);
			App.closeQueitly(conn);
		}
		
	}
	
	// Delete Inventory
	public static void deleteInventory(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("DELETE FROM GroceryInventory WHERE GroceryInventoryId = " + id + ";");
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
	}
}
