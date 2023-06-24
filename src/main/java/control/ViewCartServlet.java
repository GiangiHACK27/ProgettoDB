package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Game;
import java.util.ArrayList;

@WebServlet("/ViewCartServlet")
public class ViewCartServlet extends BaseServlet {

    public ViewCartServlet() {
        super();
    }
	
	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get cart from servlet
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/RetrieveCartServlet?category=cart");
			dispatcher.include(request, response);
		}
		//get cart from servlet

		//Initialize cart that will be sent in response
		List<Game> cartItems = new ArrayList<>();
		//Initialize cart that will be sent in response

		//get cart from request taken from retrievecartservlet
		Cart cart = (Cart) request.getAttribute("cartForView");
		//get cart from request taken from retrievecartservlet

		//For each gameID, get all game data from RetrieveGameServlet
		if(cart != null) {
			for(int id : cart.getGames()) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/RetrieveGameServlet?gameId="+id);
				dispatcher.include(request, response);
				cartItems.add((Game) request.getAttribute("game"));
			}
		}
		//For each gameID, get all game data from RetrieveGameServlet

		request.setAttribute("cartItems", cartItems);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private static final long serialVersionUID = 7421160416673640068L;
}
