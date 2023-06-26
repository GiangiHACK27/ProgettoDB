package control;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import dao.InterestedDAO;
import model.Cart;
import model.Interested;
import model.User;

@WebServlet("/DeleteFromCartServlet")
public class DeleteFromCartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteFromCartServlet() {
        super();
    }

	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Retrieve from session the user info
		User user = (User) session.getAttribute("user");
		//Retrieve from session the user info
		
		//Validate parameters
		List<String> parameters = new ArrayList<>(Arrays.asList("gameId", "category"));
		
		if(! validParameters(request, response, parameters))
			return;
		//Validate parameters
		
		//Retrieve game id from request
		Integer gameToRemove = Integer.parseInt(request.getParameter("gameId"));
		//Retrieve game id from request
		
		//Retrieve category of cart from request
		Interested.Category category = Interested.Category.valueOf(request.getParameter("category").toUpperCase());
		//Retrieve category of cart from request
	
		
		
		//In case user is logged
		if(user != null) {
			//Create dao and dto for interest
			InterestedDAO interestedDAO = new InterestedDAO((DataSource)getServletContext().getAttribute("DataSource"));
			
			Interested interested = new Interested();
			interested.setGameId(gameToRemove);
			interested.setUsername(user.getUsername());
			interested.setCategory(category);
			//Create dao and dto for interest
			
			//Add interest to the database
			try {
				interestedDAO.removeInterest(interested);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//Add interest to the database
		}
		//In case user is logged
		
		//In case user isn't logged
		else {
			//Retrieve Cart from session
			Cart cart = (Cart)request.getSession().getAttribute(category.toString().toLowerCase());
			
			if(cart == null) 
				return;
			
			try {
				cart.removeGame(gameToRemove);	
			} catch(InvalidParameterException e) {
				e.printStackTrace();
			}
			//Retrieve Cart from session
		}
		//In case user isn't logged
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
