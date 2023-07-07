package control;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.InvalidParameters;

@WebServlet("/user/PurchaseRedirectServlet")
public class PurchaseRedirectServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public PurchaseRedirectServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Check if parameters are empty
		if( ! validParameters(request, response, Arrays.asList("from", "gameId")) ) {
			throw new InvalidParameters();
		}
		//Check if parameters are empty
		
		//Retrieve parameters from request
		String from = request.getParameter("from");
		String gameId = request.getParameter("gameId");
		//Retrieve parameters from request
		
		//Check if parameters are valid
		if( ! (from.equals("personalGamePage") || from.equals("cart")) ) 
			throw new InvalidParameters();
		//Check if parameters are valid
		
		//Decide on base of parameter what server you want to call
		String servletToCall = "PurchaseFromCartServlet?category=cart";
		if( from.equals("personalGamePage") ) {
			servletToCall = "AddPurchaseGameServlet?gameId=" + gameId;
		}
		
		request.setAttribute("servletToCall", servletToCall);
		//Decide on base of parameter what server you want to call
		
		//Forward request and response to the jsp page of the purchase
		RequestDispatcher dispatcher = request.getRequestDispatcher("Purchase.jsp");
		dispatcher.forward(request, response);
		//Forward request and response to the jsp page of the purchase
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
