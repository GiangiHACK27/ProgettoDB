package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

import model.User;
import model.UserDAO;

@WebServlet("/common/RegisterServlet")

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = -7804549238416977869L;

	public RegisterServlet(){
		super();
	}
	
	private String toHash(String password) throws NoSuchAlgorithmException {
		String hashString = null;
		//convert password to hashed version
		java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		hashString = "";
		
		for (int i = 0; i < hash.length; i++) {
			hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3);
		}
		//convert password to hashed version
		return hashString;
	}
	
	private boolean isNotValidParam(String s) {
    	return s == null || s.trim().isEmpty();
    }
	
	private void errorRegister(HttpServletRequest request, HttpServletResponse response) {
    	RequestDispatcher rs = request.getRequestDispatcher("/Content/common/Register.jsp");
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
		
		//Retrieve form inputs and check if they're valid
		Enumeration <String> attributes = request.getAttributeNames();
		
		while(attributes.hasMoreElements()) {
			String attributeName = attributes.nextElement();
			String attributeValue = request.getParameter(attributeName);
			if(isNotValidParam(attributeValue)) {
				request.setAttribute("logError", "Missing" + attributeName);
				errorRegister(request, response);
				return;
			}
			request.setAttribute(attributeName +"Old", attributeValue);
		}
		//Retrieve form inputs and check if they're valid
		
		//Get user data from request
		String password =  request.getParameter("password");
		String username =  request.getParameter("username");
		String email = request.getParameter("email");
		System.out.println(password);
		//Get user data from request

		//Hash the password
		String hashPassword = null;	
		try {
			hashPassword = toHash(password);
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
