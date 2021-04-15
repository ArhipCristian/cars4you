package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MCookies;
import manager.MLogin;
import util.Restriction;
import util.ResultValidation;
import util.Validation;
import entities.SingleEntry; 
import entities.User;

public class ActionLogin {
	
	public static void loginAttempt(HttpServletRequest request, HttpServletResponse response) {		
		HashMap<String, String> hm_login = new HashMap<String, String>();		
		HashMap<String, String> hm_errorMsg = new HashMap<String, String>();		
		
		hm_login.put("login", request.getParameter("login").toLowerCase());
		hm_login.put("password", request.getParameter("password"));	

			User user = MLogin.login(hm_login.get("login"), hm_login.get("password"));			
			
			if(user != null) {				
				hm_errorMsg.put("loginState", "ok");
				request.getSession().setAttribute("user", user);
			}
			else {
				hm_errorMsg.put("loginState", "badEmailPassword");
			}
                        		request.setAttribute("hm_login", hm_login);
		request.setAttribute("hm_errorMsg", hm_errorMsg);
	}
	
	public static User getUserFromAutoLogin(HttpServletRequest request) {
		User user = null;
		if(MCookies.exist("id", request) && MCookies.exist("token", request)) {
			
			SingleEntry<Integer, String> se_idToken = new SingleEntry<Integer, String>(Integer.parseInt(MCookies.getValue("id", request)), MCookies.getValue("token", request));
			user = MLogin.getUserFromAutoLogin(se_idToken);
		}
		
		return user;
	}	
	
	private static String getErrorMsgForEmail(ResultValidation resValid) {
		String errorMsg = "";
		Restriction restriction = resValid.getRestriction();
		switch(resValid.getCode()) {
			case 1:
				errorMsg += "Vous devez remplir le champ.\n";
			case 2:
				if(restriction.isMinLength())
					errorMsg += "Vous devez saisir au moins "+restriction.getMinLength()+" caractère(s).\n";
			case 3:
				if(restriction.isMaxLength())
					errorMsg += "Vous devez saisir un maximum de "+restriction.getMaxLength()+" caractère(s).\n";
			case 4:
				errorMsg += "Veuillez saisir une adresse email Valide.\n";
				break;
			case 5:
				break;
		}
		
		return errorMsg;
	}
	
	private static String getErrorMsgForPassword(ResultValidation resValid) {
		String errorMsg = "";
		Restriction restriction = resValid.getRestriction();
		switch(resValid.getCode()) {
			case 1:
				errorMsg += "Vous devez remplir le champ.\n";
			case 2:
				if(restriction.isMinLength())
					errorMsg += "Vous devez saisir au moins "+restriction.getMinLength()+" caractère(s).\n";
			case 3:
				if(restriction.isMaxLength())
					errorMsg += "Vous devez saisir un maximum de "+restriction.getMaxLength()+" caractère(s).\n";
			case 4:
				errorMsg += "Veuillez entrez une mot de passe comprenand au moins 1 minuscule, 1 majuscule, 1 chiffre et 1 caractère spécial.";
			case 5:
				break;
		}
		
		return errorMsg;
	}
}
