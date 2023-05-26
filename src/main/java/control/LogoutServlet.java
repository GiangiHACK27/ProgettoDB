package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/user/LogoutServlet")
public class LogoutServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	protected void clearSessionUser(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("cart", null);
		request.getSession().setAttribute("whishlist", null);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		clearSessionUser(request);
		response.sendRedirect(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
