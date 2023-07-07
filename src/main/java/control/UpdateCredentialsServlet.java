package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import utility.Hasher;

import dao.UserDAO;

import model.User;

@WebServlet("/UpdateUserServlet")
public class UpdateCredentialsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCredentialsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("NON HO FATTO IL BACKEND!!! ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Pattern mailPattern = Pattern.compile("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", Pattern.CASE_INSENSITIVE);
	    Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$");
	    
	    User user = (User) request.getSession().getAttribute("user");
	    
	    //Check if parameter are valid
	    List<String> parametersToCheck = Arrays.asList("password", "email");
	    
	    if( ! validParameters(request, response, parametersToCheck))
	    	return;
	    //Check if parameter are valid
	    
	    //Retrieve parameters from the request
	    String password = request.getParameter("password");
	    String email = request.getParameter("email");
	    //Retrieve parameters from the request
	    
		//Check if password and email are valid with regex
		if(! mailPattern.matcher(email).find() || ! passwordPattern.matcher(password).find()) {
			response.sendError(404);
			return;
		}
		//Check if password and email are valid with regex
	    
		//Hash the password
		String hashPassword = null;	
		try {
			hashPassword = Hasher.toHash(password);
		} catch (NoSuchAlgorithmException e) {
			response.sendError(500);
			return;
		}
		//Hash the password
	    
		//Update the credentials on db
		DataSource dataSource = (DataSource)request.getServletContext().getAttribute("DataSource");
		
		UserDAO userDAO = new UserDAO(dataSource);
		try {
			userDAO.updatePasswordAndEmail(hashPassword, email, user.getUsername());
		} catch (SQLException e) {
			response.sendError(500);
			return;
		}
		//Update the credentials on db
		
		//Redirect to personal area
		response.sendRedirect(request.getContextPath() + "/user/PersonalArea.jsp");
		//Redirect to personal area
	}
}
