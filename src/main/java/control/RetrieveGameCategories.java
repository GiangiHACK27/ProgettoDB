package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.Category;
import dao.BelongsDAO;

@WebServlet("/RetrieveGameCategories")
public class RetrieveGameCategories extends BaseServlet {
    
    public RetrieveGameCategories() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//retrieve game id from request
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		//retrieve game id from request
		
		//Construct categoryDAO from DataSource
		BelongsDAO categoryDAO = new BelongsDAO((DataSource)getServletContext().getAttribute("DataSource"));
		//Construct categoryDAO from DataSource
		
		List<Category> categories = null;
		try {
			//Retrieve all categories from the Database
			categories = categoryDAO.retrieveGameCategories(gameId);
			//Retrieve all categories from the Database
		} catch (SQLException e) {
			response.sendError(500);
		}
		
		//Put categories in request
		request.setAttribute("gameCategories", categories);
		//Put categories in request
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private static final long serialVersionUID = 5880376570744259267L;
}
