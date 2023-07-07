package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;

import dao.UserDAO;
import utility.BackendException;
import utility.InvalidParameters;

@WebServlet("/emailAlreadyTakenServlet")
public class EmailAlreadyTakenServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public EmailAlreadyTakenServlet() {
        super();
    }

	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set type of result
		response.setContentType("application/json");
		//Set type of result
		
		//By default return false in the response
		Boolean b = false;
		//By default return false in the response
		
		if(! validParameters(request, response, Arrays.asList("email"))) {
			throw new InvalidParameters();
		}
		
		//Retrieve from request the email
		String email = (String)request.getParameter("email");
		//Retrieve from request the email
		
		//Retrieve from servlet context the data source
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		//Retrieve from servlet context the data source
		
		//Check if on database is present a user with the same email
		UserDAO userDAO = new UserDAO(ds);
		if(email != null) {
			try {
				b = userDAO.emailAlreadyExist(email);
			} catch (SQLException e) {
				throw new BackendException();
			}
		}
		//Check if on database is present a user with the same email
		
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
