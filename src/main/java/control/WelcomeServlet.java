package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	try{
    		requestDispatcher.forward(request, response);
    	}
    	catch(ServletException | IOException e){
			//TODO: add error page
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			doGet(request, response);
		}
		catch(ServletException | IOException e){
			//TODO: add error page
		}
	}
}
