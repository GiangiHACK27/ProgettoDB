package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import dao.PurchaseDAO;

import model.Purchase;
import model.User;
import model.Game;

@WebServlet("/user/AddPurchaseGameServlet")
public class AddPurchaseGameServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public AddPurchaseGameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Can't use get in this case");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Retrieve User from session
		User user = (User)request.getSession().getAttribute("user");
		//Retrieve User from session
		
		//Check if input are valid
		if(! super.validParameters(request, response, "/GamingWorldShop"))
			return;
		//Check if input are valid
		
		//Retrieve parameters from request and build purchase
		Purchase purchase = new Purchase();
		
		String gameId = request.getParameter("gameId");
		
		purchase.setUsername(user.getUsername());
		purchase.setGameId(Integer.parseInt(gameId));

		RequestDispatcher dispatcher = request.getRequestDispatcher("../RetrieveGameServlet?gameId=" + gameId);
		dispatcher.include(request, response);

		Game game = (Game)request.getAttribute("game");
		purchase.setPrice(game.getPrice());
		//Retrieve parameters from request and build purchase

		//Retrieve datasource from the servelt context
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		//Retrieve datasource from the servelt context
		
		//Delete if is present the element from the cart
		dispatcher = request.getRequestDispatcher("../DeleteFromCartServlet?category=cart&gameId=" + gameId);
		dispatcher.include(request, response);
		//Delete if is present the element from the cart
		
		//Insert purchase on database
		PurchaseDAO purchaseDAO = new PurchaseDAO(ds);
		try {
			purchaseDAO.insert(purchase);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		//Insert purchase on database
	}
}
