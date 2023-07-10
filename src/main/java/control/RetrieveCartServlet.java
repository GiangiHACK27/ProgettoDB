package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import dao.InterestedDAO;
import dao.GameDAO;

import model.Cart;
import model.Interested;
import model.User;

import utility.BackendException;
import utility.InvalidParameters;

@WebServlet("/RetrieveCartServlet")
public class RetrieveCartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveCartServlet() {
        super();
    }
    
    protected void validateCart(HttpServletRequest request, HttpServletResponse response, Cart cart, Interested.Category category) throws ServletException, IOException, SQLException {
		GameDAO gameDAO = new GameDAO((DataSource)getServletContext().getAttribute("DataSource"));
		
		List<Integer> idToRemove = new ArrayList<>();
		
		if(cart != null) {
			for(int id : cart.getGames()) {
				if(gameDAO.isUnlisted(id)) {
					idToRemove.add(id);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/DeleteFromCartServlet?gameId=" + id + "&category=" + category.toString().toLowerCase());
					dispatcher.include(request, response);
				}
			}
			
			for(int id : idToRemove) {
				cart.removeGame(id);
			}
		}
    }
    
    /*Puts a cart item in the request. Needs a "category" in the GET request */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Retrieve from session the user info
		User user = (User) session.getAttribute("user");
		//Retrieve from session the user info
		
		//Validate parameters
		if(! validParameters(request, response, Arrays.asList("category")))
			throw new InvalidParameters();
		//Validate parameters
		
		//Retrieve category of cart from request
		Interested.Category category = Interested.Category.valueOf(request.getParameter("category").toUpperCase());
		//Retrieve category of cart from request
		
		Cart cart = null;
		
		//In case user is logged
		if(user != null) {
			
			//Create dao for interest
			InterestedDAO interestedDAO = new InterestedDAO((DataSource)getServletContext().getAttribute("DataSource"));
			//Create dao for interest
			
			//Retrieve Cart from db
			try {
				cart = interestedDAO.retrieveCart(user.getUsername(), category);
			} catch (SQLException e) {
				throw new BackendException();
			}
			//Retrieve Cart from db
		}
		//In case user is logged
		
		//In case user isn't logged
		else {
			//Retrieve Cart from session
			cart = (Cart)request.getSession().getAttribute(category.toString().toLowerCase());
			
			if(cart != null)
				try {
					cart = cart.clone();
				} catch (CloneNotSupportedException e) {
					throw new BackendException();
				}
			//Retrieve Cart from session
		}
		//In case user isn't logged
		
		//Check if element in the cart are valid
		try {
			validateCart(request, response, cart, category);
		} catch (SQLException e) {
			throw new BackendException();
		}
		//Check if element in the cart are valid
		
		//Put the copy of cart in the request
		request.setAttribute(category.toString().toLowerCase() + "ForView", cart);
		//Put the copy of cart in the request
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

