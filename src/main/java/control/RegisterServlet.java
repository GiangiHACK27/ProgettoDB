package control;
import java.util.regex.Pattern;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.UserDAO;
import dao.InterestedDAO;

import model.Cart;
import model.Interested;

import utility.Hasher;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends BaseServlet {
	private static final long serialVersionUID = -7804549238416977869L;

	public RegisterServlet(){
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Pattern mailPattern = Pattern.compile("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", Pattern.CASE_INSENSITIVE);
	    Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$");
	    Pattern usernamePattern = Pattern.compile("^.{1,20}$");

		//First of all logout from the current user
		request.getSession().setAttribute("user", null);
		//First of all logout from the current user
		
		//Retrieve form inputs and check if they're valid
		if( ! validParameters(request, response)) {
			return;
		}
		//Retrieve form inputs and check if they're valid
		
		//Get user data from request
		String password =  request.getParameter("password");
		String username =  request.getParameter("username");
		String email = request.getParameter("email");
		//Get user data from request
		
		//Check if password and email are valid with regex
		if(!mailPattern.matcher(email).find() || !passwordPattern.matcher(password).find() || !usernamePattern.matcher(username).find()) {
			showError(request, response, "Invalid inputs", SELFPATH);
			return;
		}
		//Check if password and email are valid with regex

		//Hash the password
		String hashPassword = null;	
		try {
			hashPassword = Hasher.toHash(password);
		} catch (NoSuchAlgorithmException e) {
			showError(request, response, "Fatal error", SELFPATH);
			return;
		}
		//Hash the password

		//insert user into database
		UserDAO userDAO = new UserDAO((DataSource)getServletContext().getAttribute("DataSource"));
		try {
			userDAO.insertUser(username, hashPassword, email);
		} catch (SQLException e) {
			showError(request, response, "Credentials already taken", SELFPATH);

			return;
		}
		//insert user into database
		
		//fill db cart and empty session cart
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		if(cart != null) {
			
			DataSource dataSource = (DataSource) request.getServletContext().getAttribute("DataSource");
			InterestedDAO interestedDAO = new InterestedDAO(dataSource);
			

			
			for(int id : cart.getGames()) {
				Interested interested = new Interested();
				
				interested.setCategory(Interested.Category.CART);
				interested.setUsername(username);
				interested.setGameId(id);
				
				try {
					interestedDAO.insertInterest(interested);
				} catch (SQLException e) {

					break;
				}
			}
		}
		request.getSession().setAttribute("cart", null);
		//fill db cart and empty session cart
		
		//login as new user
		RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet?newUser=true");
		dispatcher.forward(request, response);
		//login as new user
	}
	private static final String SELFPATH = "/Register.jsp";
}
