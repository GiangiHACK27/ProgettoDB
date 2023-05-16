package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Category;
import dao.CategoryDAO;

@WebServlet("/RetrieveAllCategories")
public class RetrieveAllCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveAllCategories() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Construct categoryDAO from DataSource
		CategoryDAO categoryDAO = new CategoryDAO((DataSource)getServletContext().getAttribute("DataSource"));
		//Construct categoryDAO from DataSource
		
		List<Category> categories = null;
		try {
			//Retrieve all categories from the Database
			categories = categoryDAO.retrieveAllCategories();
			//Retrieve all categories from the Database
		} catch (SQLException e) {
			response.sendError(500);
		}
		
		//Put categories in request
		request.setAttribute("categories", categories);
		//Put categories in request
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
