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
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/RetrieveCartServlet?category=cart");
			dispatcher.include(request, response);
		}
		List<Game> cartItems = new ArrayList<>();
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		if(cart != null) {
			for(int id : cart.getGames()) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/RetrieveGameServlet?gameId="+id);
				dispatcher.include(request, response);
				cartItems.add((Game) request.getAttribute("game"));
			}
		}
		request.setAttribute("cartItems", cartItems);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private static final long serialVersionUID = 7421160416673640068L;
}
