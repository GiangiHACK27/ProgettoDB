package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.GameDAO;
import model.Category;
import model.Game;
import model.Game.Pegi;

@WebServlet("/SearchGames")
public class SearchGames extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchGames() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//Include Retrieve all categories Servlet 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RetrieveAllCategories");
		dispatcher.include(request, response);
		//Include Retrieve all categories Servlet
		
		//Retrieve all paramaters from form
		List<Category> categoriesToSearch;
		String[] parameterCategories = request.getParameterValues("categories");
		if(parameterCategories == null)
			categoriesToSearch = (List<Category>)request.getAttribute("categories");
		else {
			categoriesToSearch = new ArrayList<>();
			
			for(String p : parameterCategories) {
				categoriesToSearch.add(new Category(p));
			}
		}
		
		int currentMaxPrice = (Integer)request.getServletContext().getAttribute("maxPrice");
		String t = request.getParameter("currentMaxPrice");
		if(t != null)
			currentMaxPrice = Integer.parseInt(t);
		
		int pegi = Pegi.PEGI_18.getValue();
		t = request.getParameter("pegi");
		if(t != null)
			pegi = Integer.parseInt(t);
		
		String searchText = request.getParameter("searchBar");
		if(searchText == null) 
			searchText="";
		//Retrieve all paramaters from form
		
		//Retrieve all Games from database
		GameDAO gameDAO = new GameDAO((DataSource)request.getServletContext().getAttribute("DataSource"));
		List<Game> games = null;
		
		try {
			games = gameDAO.retrieveGames(categoriesToSearch, currentMaxPrice, pegi, searchText);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("games", games);
		//Retrieve all Games from database
		
		for(Game g : games) {
			System.out.println(g.getName());
		}
		
		//Forward request to view for visualize results of search
		String noRedirect = request.getParameter("noRedirect");
		if(noRedirect == null) {
			dispatcher = request.getRequestDispatcher("/Catalog.jsp");
			dispatcher.forward(request, response);
		}
		//Forward request to view for visualize results of search
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
