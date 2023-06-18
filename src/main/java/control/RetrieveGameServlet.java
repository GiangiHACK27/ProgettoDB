package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import dao.GameDAO;
import dao.SystemRequirementDAO;
import model.Game;
import model.SystemRequirement;

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
		
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		
		//Retrieve the game from database
		GameDAO gameDAO = new GameDAO(ds);
		Game game = null;
		try {
			game = gameDAO.retrieveGame(gameId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Retrieve the game from database
		
		//Retrieve the system requirement from the game
		SystemRequirementDAO srDAO = new SystemRequirementDAO(ds);
		List<SystemRequirement> requirements = null;
		try {
			requirements = srDAO.retrieveAllSystemRequirement(gameId);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		Map<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirementsMap = 
				requirements.stream().collect(Collectors.groupingBy((r) -> r.getOs()));
		//Retrieve the system requirement from the game
		
		//TODO: check if the user already buyed the game
		
		//TODO: check if the user already buyed the game
		
		//Place in the request the game and requirements
		request.setAttribute("game", game);
		request.setAttribute("requirements", requirementsMap);
		//Place in the request the game and requirements
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
