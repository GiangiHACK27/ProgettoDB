package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import model.Category;

public class CategoryDAO extends BaseDAO {

	public CategoryDAO(DataSource ds) {
		super(ds);
	}

	public List<Category> retrieveAllCategories() throws SQLException {
		
		List<Category> categories = null;
		
		String query = "SELECT * FROM Category";
		
		//Retrieve connection
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
		//Retrieve connection
			
			//Retrieve the categories from database 
			ResultSet rs = ps.executeQuery();
			//Retrieve the categories from database
					
			//Create list of retrieved Categories
			categories = new ArrayList<>();
			
			while(rs.next()) {
				String name = rs.getString("name");
				Category category = new Category(name);
				categories.add(category);
			}
			//Create list of retrieved Categories
		}
		
		return categories;
	}
}
