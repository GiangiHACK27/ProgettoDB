package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import javax.sql.DataSource;

import dao.GameDAO;

@WebServlet("/usernameAlreadyTakenServlet")
public class UsernameAlreadyTakenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsernameAlreadyTakenServlet() {
        super();
    }

	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set type of result
		response.setContentType("application/json");
		//Set type of result
		
		//By default return false in the response
		Boolean b = false;
		//By default return false in the response
		
		//Retrieve from request the username
		String username = (String)request.getParameter("username");
		//Retrieve from request the username
		
		//Retrieve from servlet context the data source
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		//Retrieve from servlet context the data source
		
		//Check if on database is present a user with the same username
		GameDAO gameDAO = new GameDAO(ds);
		if(username != null) {
			try {
				b = gameDAO.usernameAlreadyExist(username);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//Check if on database is present a user with the same username
		
		//Put in response result
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("result", b);
		out.print(json.toString());
		//Put in response result
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
