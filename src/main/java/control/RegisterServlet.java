package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.UserDAO;
import utility.Hasher;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = -7804549238416977869L;

	public RegisterServlet(){
		super();
	}
	
	private boolean isNotValidParam(String s) {
    	return s == null || s.trim().isEmpty();
    }
	
	private void errorRegister(HttpServletRequest request, HttpServletResponse response) {
    	RequestDispatcher rs = request.getRequestDispatcher("/Register.jsp");
    	try {
			rs.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath());
		}
		else {
			request.setAttribute("logError", null);
			errorRegister(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//First of all logout from the current user
		request.getSession().setAttribute("user", null);
		//First of all logout from the current user
		
		//Retrieve form inputs and check if they're valid
		Enumeration <String> parameters = request.getParameterNames();
		
		while(parameters.hasMoreElements()) {
			String parameterName = parameters.nextElement();
			String parameterValue = request.getParameter(parameterName);
			if(isNotValidParam(parameterValue)) {
				request.setAttribute("logError", "Missing " + parameterName);
				errorRegister(request, response);
				return;
			}
		}
		//Retrieve form inputs and check if they're valid
		
		//Get user data from request
		String password =  request.getParameter("password");
		String username =  request.getParameter("username");
		String email = request.getParameter("email");
		//Get user data from request

		//Hash the password
		String hashPassword = null;	
		try {
			hashPassword = Hasher.toHash(password);
		} catch (NoSuchAlgorithmException e) {
			request.setAttribute("logError","Fatal error");
			errorRegister(request, response);
			return;
		}
		//Hash the password

		//insert user into database
		UserDAO userDAO = new UserDAO((DataSource)getServletContext().getAttribute("DataSource"));
		try {
			userDAO.insertUser(username, hashPassword, email);
		} catch (SQLException e) {
			request.setAttribute("logError","Username is already taken"); //in realtà potrebbe essere anche la mail
			errorRegister(request, response);
			return;
		}
		//insert user into database

		response.sendRedirect(request.getContextPath());	
	}
}
