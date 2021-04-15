package servlet;

import action.ActionCategory;
import action.ActionItems;
import action.ActionCart;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "index", urlPatterns = { "/home" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                ActionItems.getItems(request, response);
                ActionCategory.getCategories(request, response);
		
		request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                String strId = request.getParameter("itemId");
		String strQty = request.getParameter("qty");
	 
		ActionCart.addItem(request, response, strId, strQty);
		doGet(request, response);
	}
}
