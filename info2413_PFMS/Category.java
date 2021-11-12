package info2413_PFMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Category {
	String categoryName;
	float totalSpent;
	
	// Category has a list of GroceryItems that belong to the category
	ArrayList<GroceryItem> items = new ArrayList<GroceryItem>();
	
	// Constructor
	Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	// Getters and setters
	public void setCategoryName(String newCategoryName) {
		categoryName = newCategoryName;
		// Make changes to database
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	// createCategory
	// Add category to database
	
	// deleteCategory
	// Delete category from database
	
	// updateCategory
	// Make changes to database
	
	// calculateSpending
	// Get prices from all items and add
	
	// addGroceryItems
	// Add item to list
	// ----- SQL Queries -----
	public static ArrayList<String[]> getCategories() {
		ArrayList<String[]> categories = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Category;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String[] category = new String[3];
				category[0] = rs.getString("CategoryId");
				category[1] = rs.getString("CategoryName");
				category[2] = rs.getString("TotalSpent");
				categories.add(category);
			}
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			App.closeQueitly(rs);
			App.closeQueitly(stmt);;
			App.closeQueitly(conn);
		}
		return null;
	}
	
	// Update spending
	public static void updateTotalSpending(String categoryName) {
		float totalSpent = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			conn = App.getConnection();
			stmt = conn.prepareStatement("SELECT ROUND(SUM(GroceryItem.Price * GroceryItem.Qty), 2) AS TotalSpent "
					+ "FROM GroceryItem INNER JOIN Category "
					+ "WHERE GroceryItem.Category = Category.CategoryName AND Category.CategoryName = '" + categoryName + "';");
			rs = stmt.executeQuery();
			while(rs.next()) {
				totalSpent = rs.getFloat("TotalSpent");
			}
			
			stmt1 = conn.prepareStatement("UPDATE Category "
					+ "SET TotalSpent = " + totalSpent + "WHERE CategoryName = '" + categoryName + "';");
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
}
