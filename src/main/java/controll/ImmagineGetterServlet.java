package controll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Immagine;
import model.ImmagineDAO;

@WebServlet("/ImmagineGetterServlet")
public class ImmagineGetterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ImmagineGetterServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		Immagine immagine = ImmagineDAO.getImmagineFromID(id);
		
		response.setContentType("image/png");
		ServletOutputStream out = response.getOutputStream();
		
		out.write(immagine.getBytes());
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
