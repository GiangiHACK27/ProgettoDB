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
import model.Interested;
import model.User;

@WebServlet("/EmptyCartServlet")
public class EmptyCartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public EmptyCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Retrieve from session the user info
		User user = (User) session.getAttribute("user");
		//Retrieve from session the user info
			
		//Validate parameters
		if(! validParameters(request, response, "/GamingWorldShop"))
			return;
		//Validate parameters
		
		//Retrieve category of cart from request
		Interested.Category category = Interested.Category.valueOf(request.getParameter("category").toUpperCase());
		//Retrieve category of cart from request	
		
		//In case user is logged
		if(user != null) {
			//Create dao and remove cart
			InterestedDAO interestedDAO = new InterestedDAO((DataSource)getServletContext().getAttribute("DataSource"));
			
			try {
				interestedDAO.removeCart(user.getUsername(), category);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//Create dao and remove cart
		}
		//In case user is logged
		
		//In case user isn't logged
		else {
			//Set Cart to null in the session
			request.getSession().setAttribute(category.toString().toLowerCase(), null);
			//Set Cart to null in the session
		}
		//In case user isn't logged
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
