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

import dao.GameDAO;
import dao.PurchaseDAO;

import model.Purchase;
import model.User;
import utility.BackendException;
import utility.InvalidParameters;
import model.Game;

@WebServlet("/user/AddPurchaseGameServlet")
public class AddPurchaseGameServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public AddPurchaseGameServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Retrieve User from session
		User user = (User)request.getSession().getAttribute("user");
		//Retrieve User from session
		
		//Check if input are valid
		if(! super.validParameters(request, response, Arrays.asList("gameId"))) {
			throw new InvalidParameters();
		}
		//Check if input are valid
		
		//Retrieve datasource from the servelt context
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		//Retrieve datasource from the servelt context
		
		//Retrieve gameId and check if the game is unlisted
		String gameId = request.getParameter("gameId");
		
		GameDAO gameDAO = new GameDAO(ds);
		
		boolean isUnlisted = false;
		try {
			isUnlisted = gameDAO.isUnlisted(Integer.parseInt(gameId));
		} catch (NumberFormatException | SQLException e) {
			throw new BackendException();
		}
		
		if(isUnlisted)
			throw new InvalidParameters();
		//Retrieve gameId and check if the game is unlisted
		
		//Build purchase
		Purchase purchase = new Purchase();
		
		purchase.setUsername(user.getUsername());
		purchase.setGameId(Integer.parseInt(gameId));

		RequestDispatcher dispatcher = request.getRequestDispatcher("../RetrieveGameServlet?gameId=" + gameId);
		dispatcher.include(request, response);

		Game game = (Game)request.getAttribute("game");
		purchase.setPrice(game.getPrice());
		//Build purchase
		
		//Delete if is present the element from the cart
		dispatcher = request.getRequestDispatcher("../DeleteFromCartServlet?category=cart&gameId=" + gameId);
		dispatcher.include(request, response);
		//Delete if is present the element from the cart
		
		//Insert purchase on database
		PurchaseDAO purchaseDAO = new PurchaseDAO(ds);
		try {
			purchaseDAO.insert(purchase);
		} catch (SQLException e) {
			throw new BackendException();
		}
		//Insert purchase on database
		
		//Redirect to success page
		response.sendRedirect("/GamingWorldShop/Success.jsp");	
		//Redirect to success page
	}
}
