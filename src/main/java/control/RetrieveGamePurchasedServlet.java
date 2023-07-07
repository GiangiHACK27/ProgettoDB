package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import model.User;
import utility.BackendException;
import model.Game;
import model.Purchase;
import dao.GameDAO;

@WebServlet("/user/RetrieveGamePurchasedServlet")
public class RetrieveGamePurchasedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveGamePurchasedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retrieve username from request
		User user = (User) request.getSession().getAttribute("user");
		//Retrieve username from request
		
		//Retrieve data source from servlet context
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		//Retrieve data source from servlet context
		
		//Retrieve all purchased game from db
		GameDAO gameDAO = new GameDAO(ds);
		
		List<AbstractMap.SimpleEntry<Game, Purchase>> gamePurchased = null;
		
		try {
			gamePurchased = gameDAO.retrievePurchasedGameForUsername(user.getUsername());
		} catch (SQLException e) {
			throw new BackendException();
		}
		//Retrieve all purchased game from db
		
		request.setAttribute("gamePurchased", gamePurchased);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
