package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionCart;

import util.Const;


@WebServlet(name = "cart", urlPatterns = { "/cart" })
public class ServletCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Const.PATH_PAGE_CART).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		ActionCart.updateRemovedItem(request);		
		boolean qtyValide = ActionCart.validateQty(request);		
		
		if (request.getParameter("checkout") != null && qtyValide){			
			
			ActionCart.updateCart(request);			
			request.getRequestDispatcher(Const.PATH_PAGE_SUMMARY).forward(request, response);
                        
		}else if (request.getParameter("back") != null && qtyValide){				
			
			ActionCart.updateCart(request);			
			response.sendRedirect("home");
		}
		else if (request.getParameter("refresh") != null && qtyValide){				
			
			ActionCart.updateCart(request);			
			response.sendRedirect("cart");
		}else
			doGet(request, response);
	}

}
