package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Cart;
import entities.Item;
import entities.ItemCart;
import entities.Order;
import entities.User;

public class MOrder {
	public static int add(User user, Cart cart) {
		
		int orderId = 0;
		
		try {
			
			MDB.connect();

			
			String query = "INSERT INTO `order` (`user_id`, `date`) VALUES (?, now())";
			
			PreparedStatement ps = MDB.getPS(query, 1);

			ps.setInt(1, user.getId());

			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
            
			if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }		
			
			for (ItemCart itemC : cart.getCart().values()) {
				
				query = "INSERT INTO `order_info` (`id`, `product_id`, `qty`, `price`) VALUES (?, ?, ?, ?)";
				ps = MDB.getPS(query);
				
				ps.setInt(1, orderId);
				ps.setInt(2, itemC.getId());
				ps.setInt(3, itemC.getQty());
				ps.setDouble(4, itemC.getPrice());
				
				ps.executeUpdate();				
			}		
		} catch (SQLException e) {
			e.printStackTrace();
                        
		} finally {
			MDB.disconnect();
		}

		return orderId;
		
	}	
}
