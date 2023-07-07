package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import model.Cart;
import model.User;
import utility.BackendException;
import dao.PurchaseDAO;

@WebServlet("/RetrieveGameStatusServlet")
public class RetrieveGameStatusServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveGameStatusServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		//Validate parameters
		if(! validParameters(request, response, Arrays.asList("gameId"))) {
			return;
		}
		//Validate parameters
		
		//Retrieve the user from the session
		User user = (User)request.getSession().getAttribute("user");
		//Retrieve the user from the session
		
		//Retrieve from request gameId
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		//Retrieve from request gameId
		
		//Retrieve cart and check if gameId is in the cart
		RequestDispatcher dispatcher = request.getRequestDispatcher("RetrieveCartServlet?category=cart");
		dispatcher.include(request, response);
		
		Cart cart = (Cart)request.getAttribute("cartForView");
		
		boolean isInCart = false;
		if(cart != null)
			isInCart = cart.isInCart(gameId);
		//Retrieve cart and check if gameId is in the cart
		
		//Retrieve wishlist and check if gameId is in the wishlist
		dispatcher = request.getRequestDispatcher("RetrieveCartServlet?category=wishlist");
		dispatcher.include(request, response);
		
		Cart wishlist = (Cart)request.getAttribute("wishlistForView");
		
		boolean isInWishlist = false;
		if(wishlist != null)
			isInWishlist = wishlist.isInCart(gameId);
		//Retrieve wishlist and check if gameId is in the wishlist
		
		//Check if the game is buyed
		DataSource dataSource = (DataSource)request.getServletContext().getAttribute("DataSource");
		PurchaseDAO purchaseDAO = new PurchaseDAO(dataSource);
		
		boolean isBuyed = false;
		try {
			if(user != null) 
				isBuyed = purchaseDAO.isBuyed(gameId, user.getUsername());
		} catch (SQLException e) {
			throw new BackendException();
		}
		//Check if the game is buyed
		
		//Put in request all the info
		request.setAttribute("isInCart", isInCart);
		request.setAttribute("isInWishlist", isInWishlist);
		request.setAttribute("isBuyed", isBuyed);
		//Put in request all the info
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
