package action;

import java.util.List;

import entities.Cart;
import entities.Order;
import entities.User;
import manager.MOrder;

public class ActionOrder {
	
	public static int process(User user, Cart cart){		
		
		int orderId = MOrder.add(user, cart);	
		return orderId;
	}	
}
