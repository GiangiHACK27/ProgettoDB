package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import model.User;
import model.UserDAO;

@WebServlet("/common/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
    
    private boolean isNotValidParam(String s) {
    	return s == null || s.trim().isEmpty();
    }
    
    private void errorLogin(HttpServletRequest request, HttpServletResponse response) {
    	RequestDispatcher rs = request.getRequestDispatcher("/Content/JSP/Login.jsp");
    	try {
			rs.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(500);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retrieve username and password from form and check if it is empty
		String username = request.getParameter("username");
		if(isNotValidParam(username)) {
			request.setAttribute("logError","Missing Username");
			errorLogin(request, response);
			return;
		}
		
		String password = request.getParameter("password");
		if(isNotValidParam(password)) {
			request.setAttribute("logError","Missing Password");
			errorLogin(request, response);
			return;
		}
		//Retrieve username and password from form and check if it is empty
		
		//Retrieve from the database the user from username
		UserDAO userDAO = new UserDAO((DataSource)getServletContext().getAttribute("DataSource"));
		User user = null;
		try {
			user = userDAO.getUserFromUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Retrieve from the database the user from username
		
		//Check if found a user in database
		if(user == null) {
			request.setAttribute("logError", "Wrong Username");
			errorLogin(request, response);
			return;
		}
		//Check if found a user in database
		
		//Check if password matches
		if(! user.getPassword().equals(password)) {
			request.setAttribute("logError", "Wrong Password");
			errorLogin(request, response);
			return;
		}
		//Check if password matches
		
		//Add attribute user in the session(to remember the login)
		request.getSession().setAttribute("user", user);
		//Add attribute user in the session(to remember the login)
	}
}
