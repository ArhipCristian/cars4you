package manager;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import util.Hash;
import entities.SingleEntry;
import entities.User;

public class MLogin {
	
	public static User login(String email, String password) {
		User user = null;
		PreparedStatement ps = null;

		try {
			MDB.connect();
			
			String query = "SELECT user.id, user.name, user.email, user.password"					
					+ " FROM user WHERE email = ? AND password = ?";
			ps = MDB.getPS(query);
			
			ps.setString(1, email);
			ps.setString(2, password);
			ps.executeQuery();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {				
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();			
		}
		
		return user;
	}
	
	public static SingleEntry<Integer, String> setAutoLogin(User user) {
		SingleEntry<Integer, String> se_idToken = null;
		if(user != null) {
			try {
				String token = Hash.SHA1(UUID.randomUUID().toString());
				
				MDB.connect();
				String query = "INSERT INTO autoLogin (`token`, `user`, `date`) VALUES (?, ?, NOW())";
				
				PreparedStatement ps = MDB.getPS(query, "id");
				ps.setString(1, token);
				ps.setInt(2, user.getId());
				
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
				
				se_idToken = new SingleEntry<Integer, String>((rs.first() ? rs.getInt(1) : -1), token);
				
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException | SQLException e) {
				e.printStackTrace();
				}
			finally {
				MDB.disconnect();
			}
		}
		else {
		}
		
		return se_idToken;
	}
	
	public static User getUserFromAutoLogin(SingleEntry<Integer, String> se_idToken) {
		User user = null;		
		PreparedStatement ps = null;

		try {
			MDB.connect();
			
			String query = "SELECT user.id, user.lastName, user.firstName, user.email, user.password"					 
					+ " FROM user"
					+ " WHERE user.id = (SELECT user FROM autoLogin WHERE id = ? AND token = ?)";
			
			ps = MDB.getPS(query);
			
			ps.setInt(1, se_idToken.key());
			ps.setString(2, se_idToken.value());
			ps.executeQuery();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {				
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();			
		}
		
		return user;
	}

}
