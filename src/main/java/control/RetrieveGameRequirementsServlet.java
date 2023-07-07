package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.SystemRequirementDAO;
import model.SystemRequirement;
import utility.BackendException;

@WebServlet("/RetrieveGameRequirementsServlet")
public class RetrieveGameRequirementsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveGameRequirementsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(! super.validParameters(request, response)) 
			return;
		
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		
		//Retrieve the id of game from request
		Integer gameId = Integer.parseInt(request.getParameter("gameId"));
		//Retrieve the id of game from request
		
		//Retrieve the system requirement from the game
		SystemRequirementDAO srDAO = new SystemRequirementDAO(ds);
		List<SystemRequirement> requirements = null;
		try {
			requirements = srDAO.retrieveAllSystemRequirement(gameId);
		} catch(SQLException e) {
			throw new BackendException();
		}
		
		Map<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirementsMap = 
				requirements.stream().collect(Collectors.groupingBy(r -> r.getOs()));
		//Retrieve the system requirement from the game
		
		request.setAttribute("requirements", requirementsMap);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
