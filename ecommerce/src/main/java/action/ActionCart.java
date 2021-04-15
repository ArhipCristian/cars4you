package action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Misc;
import manager.MItem;
import manager.MSession;
import entities.Cart;
import entities.Item;
import entities.ItemCart;

public class ActionCart {

	public static Cart getCart(HttpServletRequest request) {
		HttpSession session = MSession.getSession(request);
		Cart cart = (Cart) session.getAttribute("cart");

		return cart;
	}

	public static void removeItem(String serial, HttpServletRequest request) {
		Cart cart = getCart(request);
		cart.removeItem(serial);
	}

	public static void addItem(HttpServletRequest request,
			HttpServletResponse response, String strId, String strQty) {

		if ((Misc.isNumeric(strQty) || strQty.equals("")) && Misc.isNumeric(strId)) {			
			
			int qty = 1;
			if(strQty != "")
				qty = Integer.parseInt(strQty);	
			
			if (qty > 0) {
				int id = Integer.parseInt(strId);
                                
				HttpSession session = MSession.getSession(request);
				Cart cart = (Cart) session.getAttribute("cart");

				Item item = MItem.getItemById(id);

				if (item != null) {
					ItemCart itemCart = null;
					
					if (cart.getCart().get(item.getName()) != null) {
						itemCart = cart.getCart().get(item.getName());
						itemCart.setQty(itemCart.getQty() + qty);
						
					} else {
						itemCart = new ItemCart(item);
						itemCart.setQty(qty);
					}

					
					cart.addItem(itemCart.getName(), itemCart);
				}

				session.setAttribute("cart", cart);
			}
		}
	}	
	
	public static boolean validateQty(HttpServletRequest request){
		
		boolean valid = true;
		
		HttpSession session = MSession.getSession(request);		
		
		entities.Cart cart = (entities.Cart)session.getAttribute("cart");
		
		for (ItemCart itemC : cart.getCart().values()) {
			String strQty = request.getParameter("qty-" + itemC.getName());
			if (! Misc.isNumeric(strQty))
				valid = false;
			else if (Integer.parseInt(strQty) < 1){
				valid = false;
			}
		}
		
		return valid;
	}

	public static void updateRemovedItem(HttpServletRequest request){
		
		HttpSession session = MSession.getSession(request);		
		
		Cart cart = (Cart)session.getAttribute("cart");		
		
		Enumeration<String> postList = request.getParameterNames();		
		
		while (postList.hasMoreElements()){
			String next = postList.nextElement();
			if (next.contains("p-del")){
	        	
	        	String key = next.substring(6);
	        	cart.removeItem(key);
	        	System.out.println("Removing item: " + key);
			}
	     }
		
	}	
	
	public static void updateCart(HttpServletRequest request){
		
		HttpSession session = MSession.getSession(request);	
		
		entities.Cart cart = (entities.Cart)session.getAttribute("cart");
		
		for (ItemCart itemC : cart.getCart().values()) {
			int qty = Integer.parseInt(request.getParameter("qty-" + itemC.getName()));
			itemC.setQty(qty);
		}
	}
	
}
