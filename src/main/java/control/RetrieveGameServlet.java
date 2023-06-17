package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import dao.GameDAO;
import model.Game;

@WebServlet("/RetrieveGameServlet")
public class RetrieveGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveGameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//Retrieve the id of game from request
		Integer gameId = Integer.parseInt(request.getParameter("gameId"));
		//Retrieve the id of game from request
		
		//Retrieve the game from database
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		GameDAO gameDAO = new GameDAO(ds);
		Game game = null;
		try {
			game = gameDAO.retrieveGame(gameId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//Retrieve the game from database
		
		//Place in the request the game
		request.setAttribute("game", game);
		//Place in the request the game
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
