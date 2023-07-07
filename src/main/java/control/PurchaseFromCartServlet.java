package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.PurchaseDAO;
import model.Game;
import model.Purchase;
import model.User;

@WebServlet("/user/PurchaseFromCartServlet")
public class PurchaseFromCartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public PurchaseFromCartServlet() {
        super();
    }
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get user from session
		User user = (User)request.getSession().getAttribute("user");
		//Get user from session
		
		//Dispatch request for view cart servlet
		RequestDispatcher dispatcher = request.getRequestDispatcher("../ViewCartServlet");
		dispatcher.include(request, response);
		
		List<Game> cartItems = (List<Game>)request.getAttribute("cartItems");
		//Dispatch request for view cart servlet		
		
		//Delete all element from cart after purchase
		dispatcher = request.getRequestDispatcher("../EmptyCartServlet?category=cart");
		dispatcher.include(request, response);
		//Delete all element from cart after purchase
		
		//Add all purchases to database
		
		//Retrieve datasource from the servelt context
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		//Retrieve datasource from the servelt context
		
		PurchaseDAO purchaseDAO = new PurchaseDAO(ds);
		
		for (Game game : cartItems) {
			//Build purchase
			Purchase purchase = new Purchase();
			
			purchase.setUsername(user.getUsername());
			purchase.setGameId(game.getId());
			purchase.setPrice(game.getPrice());
			//Build purchase
			
			//Insert purchase on database
			try {
				purchaseDAO.insert(purchase);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
			//Insert purchase on database
		}
		//Add all purchases to database
		
		//Redirect to success page
		response.sendRedirect("/GamingWorldShop/Success.jsp");	
		//Redirect to success page
	}
}