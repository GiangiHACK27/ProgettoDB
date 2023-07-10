package control;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.User.Role;

@WebFilter(filterName = "/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter extends HttpFilter implements Filter {
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		//Convert to httpServletRequest and get session
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;		
		HttpSession session = httpServletRequest.getSession();
		//Convert to httpServletRequest and get session
		
		//Retrieve path from the request
		String path = httpServletRequest.getServletPath();
		//Retrieve path from the request
		
		synchronized (session) {
			//Retrieve user from the session
			User user = (User) session.getAttribute("user");
			//Retrieve user from the session
		
			//Check if the access is permitted, in other case, redirect to login page or error page
			if( checkForUserPath(path, user)) {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/Login.jsp");
				return;
			}
			else if(checkForAdminPath(path, user)) {
				httpServletResponse.sendError(405);
				return;
			}
			//Check if the access is permitted, in other case, redirect to login page
		
			chain.doFilter(request, response);
		}
	}
	
	//Check if the user is logged(has a session)
	private boolean checkForUserPath(String path, User user) {
		return path.contains("/user/") && user == null; 
	}
	
	//Check if the user is and admin
	private boolean checkForAdminPath(String path, User user) {
		return path.contains("/admin/") && (user == null || ! user.getRole().equals(Role.ADMIN) ); 
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		//Future implementation
	}
	
    public void destroy() {
    	//Future implementation
    }

	private static final long serialVersionUID = 1L;
}
