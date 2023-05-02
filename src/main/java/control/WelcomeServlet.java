package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Image;
import model.ImageDAO;

@WebServlet("/")

public class WelcomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4682694221976783460L;

	public WelcomeServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String destination = "/Content/JSP/Welcome.jsp";
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
    	requestDispatcher.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
