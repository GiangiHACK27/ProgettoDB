package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import dao.InterestedDAO;
import model.Cart;
import model.Interested;
import model.User;

@WebServlet("/RetrieveCartServlet")
public class RetrieveCartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveCartServlet() {
        super();
    }

    /*Puts a cart item in the request. Needs a "category" in the GET request */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Retrieve from session the user info
		User user = (User) session.getAttribute("user");
		//Retrieve from session the user info
		
		//Validate parameters
		if(!validParameters(request, response))
			return;
		//Validate parameters
		
		//Retrieve category of cart from request
		Interested.Category category = Interested.Category.valueOf(request.getParameter("category").toUpperCase());
		//Retrieve category of cart from request
		
		
		
		Cart cart = null;
		
		//In case user is logged
		if(user != null) {
			
			//Create dao for interest
			InterestedDAO interestedDAO = new InterestedDAO((DataSource)getServletContext().getAttribute("DataSource"));
			//Create dao for interest
			
			//Retrieve Cart from db
			try {
				cart = interestedDAO.retrieveCart(user.getUsername(), category);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//Retrieve Cart from db
		}
		//In case user is logged
		
		//In case user isn't logged
		else {
			//Retrieve Cart from session
			cart = (Cart)request.getSession().getAttribute(category.toString().toLowerCase());
			
			if(cart != null)
				cart = cart.clone();
			//Retrieve Cart from session
		}
		//In case user isn't logged
		
		//Put the copy of cart in the request
		request.setAttribute(category.toString().toLowerCase() + "ForView", cart);
		//Put the copy of cart in the request
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

