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
		
		//Retrieve connection
		Connection conn = ds.getConnection();
		//Retrieve connection
		
		//Retrieve the categories from database 
		String query = "SELECT * FROM Category";
		
		PreparedStatement ps = conn.prepareStatement(query);

		ResultSet rs = ps.executeQuery();
		//Retrieve the categories from database
				
		//Create list of retrieved Categories
		List<Category> categories = new ArrayList<>();
		
		while(rs.next()) {
			String name = rs.getString("name");
			Category category = new Category(name);
			categories.add(category);
		}
		//Create list of retrieved Categories
		
		//Close connection
		conn.close();
		//Close connection
		
		return categories;
	}
}
