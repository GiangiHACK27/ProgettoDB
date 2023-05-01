package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Image;
import model.ImageDAO;

@WebServlet("/ImageGetterServlet")
public class ImageGetterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ImageGetterServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		Image image = ImageDAO.getImageFromID(id);
		
		response.setContentType("image/png");
		ServletOutputStream out = response.getOutputStream();
		
		out.write(image.getBytes());
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
