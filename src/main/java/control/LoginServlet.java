package control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import dao.InterestedDAO;
import dao.UserDAO;
import model.User;
import utility.Hasher;

import java.security.NoSuchAlgorithmException;
import model.Cart;
import model.Interested;

@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = -8697651045570564505L;

	public LoginServlet() {
        super();
    }
    
//	private void errorLogin(HttpServletRequest request, HttpServletResponse response) {
//    	RequestDispatcher rs = request.getRequestDispatcher("/Login.jsp");
//    	try {
//			rs.forward(request, response);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		} 
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath());
		}
		else {
			showError(request, response, null, selfPath);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Check if the field are empty
		if(! validParameters(request, response, Arrays.asList("username", "password"))) {
			return;
		}
		//Check if the field are empty
		
		//Retrieve username and password from form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//Retrieve username and password from form
		
		//Retrieve from the database the user from username
		//Hash the password
		String hashPassword = null;
		try {
			hashPassword = Hasher.toHash(password);
		} catch (NoSuchAlgorithmException e) {
			showError(request, response, "Fatal error", selfPath);
			return;
		}
		//Hash the password

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
			showError(request, response, "User not found", selfPath);
			return;
		}
		//Check if found a user in database
		
		//Check if password matches
		if(! user.getPassword().equals(hashPassword)) {
			showError(request, response, "Wrong password", selfPath);
			return;
		}
		//Check if password matches
		

		
		//get session cart before clearing session
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//get session cart before clearing session
		
		//Clean past session
		request.getSession().invalidate();
		//Clean past session
		
		//Add attribute user in the session(to remember the login)
		request.getSession().setAttribute("user", user);
		//Add attribute user in the session(to remember the login)
		
		//if cart is not empty, replace cart that was previously stored in database with current cart
		if(cart != null) {
			
			DataSource dataSource = (DataSource) request.getServletContext().getAttribute("DataSource");
			InterestedDAO interestedDAO = new InterestedDAO(dataSource);
			try {
				interestedDAO.removeCart(username, Interested.Category.CART);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for(int id : cart.getGames()) {
				Interested interested = new Interested();
				interested.setCategory(Interested.Category.CART);
				interested.setUsername(username);
				interested.setGameId(id);
				
				try {
					interestedDAO.insertInterest(interested);
				} catch (SQLException e) {
					e.printStackTrace();				
					break;
				}
			}
		}
		//if cart is not empty, replace cart that was previously stored in database with current cart

		//Redirect to personal area
		response.sendRedirect("/GamingWorldShop/user/PersonalArea.jsp");	
		//Redirect to personal area
	}
	private String selfPath =  "/Login.jsp";
}
