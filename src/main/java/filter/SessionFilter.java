package filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	} 

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String loginURL = req.getContextPath() + "/identification.xhtml";

		//boolean loggedIn = (session != null) && (session.getAttribute("sessionUser") != null);
		boolean loginRequest = req.getRequestURI().equals(loginURL);
		boolean resourceRequest = req.getRequestURI()
				.startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
		boolean ajaxRequest = "partial/ajax".equals(req.getHeader("Faces-Request"));

		if (loginRequest || resourceRequest) {
			if (!resourceRequest) { // Prevent browser from caching restricted
									// resources. See also
									// https://stackoverflow.com/q/4194207/157882
				res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																						// 1.1.
				res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				res.setDateHeader("Expires", 0); // Proxies.
			}

			chain.doFilter(request, response); // So, just continue request.
		} else if (ajaxRequest) {
			res.setContentType("text/xml");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // So, return
																	// special
																	// XML
																	// response
																	// instructing
																	// JSF ajax
																	// to send a
																	// redirect.
		} else {
			res.sendRedirect(loginURL); // So, just perform standard synchronous
										// redirect.
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
