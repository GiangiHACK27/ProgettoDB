package control;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.User;
import model.Cart;
import model.Interested;

import dao.InterestedDAO;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public AddToCartServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Retrieve from session the user info
		User user = (User) session.getAttribute("user");
		//Retrieve from session the user info
			
		
		
		//Validate parameters
		if(!validParameters(request, response, "/GamingWorldShop/welcome.jsp"))
			return;
		//Validate parameters
		
		//Retrieve game from request
		Integer gameToAdd = Integer.parseInt(request.getParameter("gameId"));
		//Retrieve game from request
		
		//Retrieve category of cart from request
		Interested.Category category = Interested.Category.valueOf(request.getParameter("category").toUpperCase());
		//Retrieve category of cart from request
		
		
		
		//In case user is logged
		if(user != null) {
			//Create dao and dto for interest
			InterestedDAO interestedDAO = new InterestedDAO((DataSource)getServletContext().getAttribute("DataSource"));
			
			Interested interested = new Interested();
			interested.setGameId(gameToAdd);
			interested.setUsername(user.getUsername());
			interested.setCategory(category);
			//Create dao and dto for interest
			
			//Add interest to the database
			try {
				interestedDAO.insertInterest(interested);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//Add interest to the database
		}
		//In case user is logged
		
		//In case user isn't logged
		else {
			//Retrieve Cart from session if present
			Cart cart = (Cart)request.getSession().getAttribute(category.toString().toLowerCase());
			//Retrieve Cart from session if present

			//Make new cart if there wasn't a cart present
			if(cart == null) {
				cart = new Cart("guest");
				request.getSession().setAttribute(category.toString().toLowerCase(), cart);
			}
			//Make new cart if there wasn't a cart present

			//Add game to cart
			try {
				cart.addGame(gameToAdd);	
			} catch(InvalidParameterException e) {
				e.printStackTrace();

			}
			//Add game to cart		
		}
		//In case user isn't logged
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
