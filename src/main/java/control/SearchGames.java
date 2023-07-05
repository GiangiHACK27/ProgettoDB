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

import model.User;
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
		
		boolean unListed = false;
		User user = (User) request.getSession().getAttribute("user");
		if(user != null && user.getRole().equals(User.Role.ADMIN))
			unListed = true;
		
		int currentMaxPrice = (Integer)request.getServletContext().getAttribute("maxPrice");
		if(unListed)
			currentMaxPrice = (Integer)request.getServletContext().getAttribute("maxPriceUnlisted");
		String t = request.getParameter("currentMaxPrice");
		if(t != null)
			currentMaxPrice = Integer.parseInt(t);
		
		System.out.println(request.getServletContext().getAttribute("maxPrice"));
		System.out.println(request.getServletContext().getAttribute("maxPriceUnlisted"));
		
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
		if(order == null) 
			order = "releaseDate";
		//Retrieve all parameters from form
		
		//Retrieve all Games from database
		GameDAO gameDAO = new GameDAO((DataSource)request.getServletContext().getAttribute("DataSource"));
		
		List<Game> games = null;	
		int size = 0;
		
		try {
			games = gameDAO.retrieveGames(categoriesToSearch, currentMaxPrice, pegi, searchText, order, 
					sizeOfPagination, 
					sizeOfPagination * (page - 1), unListed);
			
			size = gameDAO.countGames(categoriesToSearch, currentMaxPrice, pegi, searchText, unListed);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		//Retrieve all Games from database

		//limit games sent based on page requested
		request.setAttribute("numberOfGames", size);
		request.setAttribute("games", games);
		//limit games sent based on page requested
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static final int sizeOfPagination = 10;
}
