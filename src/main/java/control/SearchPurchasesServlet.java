package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONObject;

import dao.PurchaseDAO;

import model.Purchase;

@WebServlet("/user/SearchPurchasesServlet")
public class SearchPurchasesServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchPurchasesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		//validate parameters
		if(! validParameters(request, response, Arrays.asList("minDate", "maxDate")))
			return;
		//validate parameters
		
		//Retrieve parameter from request
		String username = request.getParameter("username");
		String minDate = request.getParameter("minDate");
		String maxDate = request.getParameter("maxDate");
		//Retrieve parameter from request
		
		//Retrieve data source from servlet context
		DataSource dataSource = (DataSource)request.getServletContext().getAttribute("DataSource");
		//Retrieve data source from servlet context
		
		//Retrieve purchases from db
		PurchaseDAO purchaseDAO = new PurchaseDAO(dataSource);
		
		List<Purchase> purchases = null;
		
		try {
			purchases = purchaseDAO.retrievePurchases(username, minDate, maxDate);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//Retrieve purchases from db
		
		//Convert the result in json
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("purchases", purchases);
		PrintWriter out = response.getWriter();
		out.print(jsonResponse.toString());
		//Convert the result in json
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
