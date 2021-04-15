package manager;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Hash;
import entities.User;

public class MSignUp {
	
	/* -1 : Problème de connexion
	 *  0 : L'adresse email est déjà présente dans la base de données
	 *  1 : L'ajout c'est fait sans problème
	 * */
	public static int signUp(User user) {
		int code = isExist(user);
		
		if(code == 1) {
			try {
				MDB.connect();			
				
				
				String query = "INSERT INTO user (`name`,`email`,`password`) VALUES (?, ?, ?)";
				
				PreparedStatement ps = MDB.getPS(query);
			
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPassword());				
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				MDB.disconnect();	
			}
		}
		return code;
	}
	
	/* -1 : Problème de connexion
	 *  0 : L'adresse email est déjà présente dans la base de données
	 *  1 : L'adresse email n'est pas présente dans la base de données
	 * */
	private static int isExist(User user) {
		int isExist = -1;
		
		try {
			MDB.connect();
			
			String query = "SELECT id FROM user WHERE email = ?";
			PreparedStatement ps = MDB.getPS(query);
			
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			
			isExist = (rs.first() ? 0 : 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();	
		}
		
		return isExist;
	}

	/* 	0 : Erreur d'insertion
	 *  >0 : Id (cle primaire)
	 * */
		
}
