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
		
		int page=1;
		try{
			page = Integer.parseInt(request.getParameter("page"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String order = request.getParameter("sort");
		if(order == null) order = "releaseDate";
		//Retrieve all parameters from form
		
		//Retrieve all Games from database
		GameDAO gameDAO = new GameDAO((DataSource)request.getServletContext().getAttribute("DataSource"));
		List<Game> games = null;
		
		try {
			games = gameDAO.retrieveGames(categoriesToSearch, currentMaxPrice, pegi, searchText, order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Retrieve all Games from database

		//limit games sent based on page requested
		List<Game> gamesToReturn = null;
		if(page*10-10 > games.size()) {
			gamesToReturn = games; //there aren't enough games to fulfill the request, so default behavior
		}
		else if(page*10 >= games.size()) {
			gamesToReturn = games.subList(page*10-10, games.size()); //the page won't be full
		}
		else {
			gamesToReturn = games.subList(page*10-10, page*10);
		}
		request.setAttribute("numberOfGames", games.size());

		request.setAttribute("games", gamesToReturn);
		//limit games sent based on page requested

//		for(Game g : games) {
//			System.out.println(g.getName());
//		}
//		
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
