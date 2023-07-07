package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import dao.PurchaseDAO;
import model.Purchase;
import model.User;
import utility.BackendException;

@WebServlet("/user/RetrievePurchasesServlet")
public class RetrievePurchasesForUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrievePurchasesForUsernameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retrieve username from request
		User user = (User) request.getSession().getAttribute("user");
		//Retrieve username from request
		
		//Retrieve DataSource from servletContext
		DataSource ds = (DataSource)request.getServletContext().getAttribute("DataSource");
		//Retrieve DataSource from servletContext
		
		//Retrieve purchases from db
		PurchaseDAO purchaseDAO = new PurchaseDAO(ds);
		
		List<Purchase> purchases = null;
		
		try {
			purchases = purchaseDAO.retrievePurchaseForUsername(user.getUsername());
		} catch (SQLException e) {
			throw new BackendException();
		}
		//Retrieve purchases from db
		
		request.setAttribute("purchases", purchases);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
