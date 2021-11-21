package info2413_PFMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Report {

	// Top 5 Monthly Category Spending 
	public static ArrayList<Object[]> spendingPerCategory(String today) {
		ArrayList<Object[]> topFiveSpent = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT CategoryId, CategoryName, ROUND(SUM(GroceryItem.Price * GroceryItem.Qty), 2) AS TotalSpent "
					+ "FROM GroceryItem INNER JOIN Category INNER JOIN GroceryInventory "
					+ "WHERE GroceryItem.Category = CategoryName AND GroceryInventory.GroceryInventoryId = GroceryItem.GroceryInventoryId "
					+ "AND ShopDate >= '" + today + "' - INTERVAL 30 DAY GROUP BY CategoryId ORDER BY TotalSpent DESC;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Object[] array = new Object[3];
				array[0] = rs.getInt("CategoryId");
				array[1] = rs.getString("CategoryName");
				array[2] = rs.getFloat("TotalSpent");
				topFiveSpent.add(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}

		return topFiveSpent;
	}

	// Top 5 Consumed
	public static ArrayList<Object[]> mostConsumed(String today) {
		ArrayList<Object[]> mostConsumed = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT Img, FoodName, Category, Price, ExpiryDate, ShopDate, Qty, QtyConsumed, Expired, GroceryItemId "
					+ "FROM GroceryItem INNER JOIN GroceryInventory "
					+ "WHERE GroceryInventory.GroceryInventoryId = GroceryItem.GroceryInventoryId AND ShopDate >= '" + today + "' - INTERVAL 30 DAY "
					+ "ORDER BY QtyConsumed DESC\r\n"
					+ "LIMIT 5;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Object[] array = new Object[10];
				array[0] = rs.getInt("GroceryItemId");
				array[1] = rs.getString("Img");
				array[2] = rs.getString("FoodName");
				array[3] = rs.getString("Category");
				array[4] = rs.getFloat("Price");
				array[5] = rs.getString("ExpiryDate");
				array[6] = rs.getString("ShopDate");
				array[7] = rs.getInt("Qty");
				array[8] = rs.getInt("QtyConsumed");
				array[9] = rs.getBoolean("Expired");
				mostConsumed.add(array);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return mostConsumed;
	}
	
	public static ArrayList<Object[]> mostExpired(String today) {
		ArrayList<Object[]> mostExpired = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT Img, FoodName, Category, Price, ExpiryDate, ShopDate, Qty - QtyConsumed AS NumExpired, Expired, GroceryItemId "
					+ "FROM GroceryItem INNER JOIN GroceryInventory "
					+ "WHERE Expired = true AND GroceryInventory.GroceryInventoryId = GroceryItem.GroceryInventoryId AND ShopDate >= '" + today + "' - INTERVAL 30 DAY "
					+ "ORDER BY NumExpired DESC "
					+ "LIMIT 5;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Object[] array = new Object[9];
				array[0] = rs.getInt("GroceryItemId");
				array[1] = rs.getString("Img");
				array[2] = rs.getString("FoodName");
				array[3] = rs.getString("Category");
				array[4] = rs.getFloat("Price");
				array[5] = rs.getString("ExpiryDate");
				array[6] = rs.getString("ShopDate");
				array[7] = rs.getInt("NumExpired");
				array[8] = rs.getBoolean("Expired");
				mostExpired.add(array);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);
			App.closeQueitly(conn);
		}
		
		return mostExpired;
	}
}
