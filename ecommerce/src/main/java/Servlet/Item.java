package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Const;
import action.ActionItems;


@WebServlet(name = "item", urlPatterns = { "/item" })
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Item() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int item;
		try {
			item = Integer.parseInt(request.getParameter("item"));
		}
		catch(NumberFormatException e) {
			item = -1;
		}
		
		ActionItems.getItemById(item, request, response);
		request.getRequestDispatcher(Const.PATH_PAGE_ITEM).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId = request.getParameter("itemId");
		String strQty = request.getParameter("qty");
	 
		
		doGet(request, response);
	}

}
