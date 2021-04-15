package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Item;

public class MItem {
    
        public static ArrayList<Item> getItems(int category){
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			MDB.connect();
			String query;
			PreparedStatement ps;
			ResultSet rs;
			
			if (category == 5){
				query = "SELECT * FROM product";
				ps = MDB.getPS(query);
			}
			else {
				query = "SELECT * FROM product WHERE category = ?";
				ps = MDB.getPS(query);
				ps.setInt(1, category);
			}
			
			rs = ps.executeQuery();
			
			while(rs.next())	
				items.add(getItemFromResultSet(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();	
		}
		return items;
	}

	public static Item getItemById(int id) {
		Item item = null;
		try {
			MDB.connect();
			String query = "SELECT * FROM product WHERE id = ?";
			
			PreparedStatement ps = MDB.getPS(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				item = getItemFromResultSet(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();
		}
		
		return item;
	}
        
	private static Item getItemFromResultSet(ResultSet rs) {

		Item item = new Item();
		
		try {
			item.setId(rs.getInt("id"));
			item.setCategory(rs.getInt("category"));
			item.setName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setPrice(rs.getDouble("price"));	
                        item.setImage(rs.getString("image"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
}
