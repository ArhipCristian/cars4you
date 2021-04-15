package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MCookies;
import util.Const;
import action.ActionLogin;


@WebServlet(name = "login", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Login() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		loginOrLogout(request, response);

		request.getRequestDispatcher(Const.PATH_PAGE_LOGIN).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loginOrLogout(request, response);
		ActionLogin.loginAttempt(request, response);
		
		@SuppressWarnings("unchecked")
		String loginState = (String)((HashMap<String, String>)request.getAttribute("hm_errorMsg")).get("loginState");
		
		if(loginState.equals("ok"))
			if(request.getParameter("fromCart") == null)
				response.sendRedirect("home");
			else
				request.getRequestDispatcher(Const.PATH_PAGE_CARTUSER).forward(request, response);
		else
				request.getRequestDispatcher(Const.PATH_PAGE_LOGIN).forward(request, response);
	}
	
	private void loginOrLogout(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession().getAttribute("user") != null) {
			
			request.getSession().removeAttribute("user");
			request.setAttribute("logout", "ok");
			MCookies.destroy("id", request, response);
			MCookies.destroy("token", request, response);
		}
	}
}
