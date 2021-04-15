package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Const;
import action.ActionCategory;
import action.ActionItems;


@WebServlet(name = "products", urlPatterns = { "/items" })
public class Items extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Items() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionCategory.getCategories(request, response);
		ActionItems.getItems(request, response);
		
		request.getRequestDispatcher(Const.PATH_PAGE_ITEMS).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId = request.getParameter("itemId");
		String strQty = request.getParameter("qty");
		
		doGet(request, response);
	}
}
