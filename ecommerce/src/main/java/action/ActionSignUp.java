package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MSignUp;
import util.Misc;
import util.Restriction;
import util.ResultValidation;
import util.Validation;
import entities.User;

public class ActionSignUp {
	
	public static boolean signUp(HttpServletRequest request, HttpServletResponse response) {
		String[] s_formParamsNeeded = {"name", "email", "confirmEmail", "password", "confirmPassword"};
				
		String[] s_formValuesNeeded = {
			request.getParameter(s_formParamsNeeded[0]),
			request.getParameter(s_formParamsNeeded[1]).toLowerCase(),
			request.getParameter(s_formParamsNeeded[2]).toLowerCase(),
			request.getParameter(s_formParamsNeeded[3]),
			request.getParameter(s_formParamsNeeded[4])};	
		
		boolean isCompleted = true;		
		
		HashMap<String, String> hm_formParamValue = new HashMap<String, String>();
		for(int i=0; i< s_formValuesNeeded.length; i++) {
			hm_formParamValue.put(s_formParamsNeeded[i], s_formValuesNeeded[i]);
		}	
		
		isCompleted = validateForm(hm_formParamValue, request);
		
		if(isCompleted) {
			
			User user = new User();				
			
			user.setId(-1);
			user.setName(hm_formParamValue.get("name"));
			user.setEmail(hm_formParamValue.get("email"));
			user.setPassword(hm_formParamValue.get("password"));
			
			int rep = MSignUp.signUp(user);
			
			if(rep < 1) {
				isCompleted = false;
				if(rep == 0)
					request.setAttribute("error", "accountExisting");
				else if(rep == -1)
					request.setAttribute("error", "DBProblem");
			}
		}		
		
		if(!isCompleted)
			request.setAttribute("hm_formParamValue", hm_formParamValue);
		
		return isCompleted;
	}
	
	public static boolean validateForm(HashMap<String, String> hm_formParamValue, HttpServletRequest request) {
		
		HashMap<String, String> hm_fieldErrorMsg = new HashMap<String, String>();		
		
		Restriction restrict1 = new Restriction(1, 45, Pattern.compile("^([a-zA-ZàéèêâïçÀÉÈÊÏÇ])+([ -][a-zA-ZàéèêâïçÀÉÈÊÏÇ]+)*"));
		
		Restriction restrictEmail = new Restriction(1, 45, Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"));
		Restriction restrictConfirmEmail = new Restriction(hm_formParamValue.get("email"));
		
		Restriction restrictPassword = new Restriction(8, 40, Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$"));
		Restriction restrictConfirmPassword = new Restriction(hm_formParamValue.get("password"));		
		
		Validation validation = new Validation(hm_formParamValue);
		validation.addRestriction("name", restrict1);
		validation.addRestriction("email", restrictEmail);
		validation.addRestriction("confirmEmail", restrictConfirmEmail);
		validation.addRestriction("password", restrictPassword);
		validation.addRestriction("confirmPassword", restrictConfirmPassword);
		
		ArrayList<ResultValidation> resultValidations = validation.validate();
		
		for(ResultValidation rv : resultValidations) {
			
			if(rv.getCode() != 0)
				hm_fieldErrorMsg.put(rv.getKey(), getErrorMsg(rv));
		}		
		
		request.setAttribute("hm_fieldErrorMsg", hm_fieldErrorMsg);

		return validation.isValidate();
	}

	private static String getErrorMsg(ResultValidation resValid) {
		String errorMsg = "";
		switch(resValid.getKey()) {
			case "name":
				errorMsg += getErrorMsgForName(resValid);
				break;
			case "email":
				errorMsg += getErrorMsgForEmail(resValid);
				break;
			case "confirmEmail":
				errorMsg += getErrorMsgForConfirmEmail(resValid);
				break;
			case "password":
				errorMsg += getErrorMsgForPassword(resValid);
				break;
			case "confirmPassword":
				errorMsg += getErrorMsgForConfirmPassword(resValid);
				break;
		}		
		return errorMsg;
	}
	
	private static String getErrorMsgForName(ResultValidation resValid) {
		String errorMsg = "";
		Restriction restriction = resValid.getRestriction();
		
		switch(resValid.getCode()) {
			case 1:
				errorMsg += "Vous devez remplir le champ nom.\n";
				break;
			case 2:
			case 3:
				errorMsg += "Vous devez saisir entre "+restriction.getMinLength()+ " et "+restriction.getMaxLength()+" caractère(s).\n";
				break;
			case 4:
				errorMsg += "Lettre seulement, pas de chiffre ou de caractère spéciaux. Espace et trait d'union accepté, sauf s'ils ont au début ou à la fin du nom)";
				break;
			case 5:
				break;
		}
		
		return errorMsg;
	}
	
	private static String getErrorMsgForEmail(ResultValidation resValid) {
		String errorMsg = "";
		Restriction restriction = resValid.getRestriction();
		
		switch(resValid.getCode()) {
			case 1:
				errorMsg += "Vous devez remplir le champ email.\n";
			case 2:
			case 3:
				errorMsg += "Vous devez saisir entre "+restriction.getMinLength()+ " et "+restriction.getMaxLength()+" caractère(s).\n";
			case 4:
				errorMsg += "Veuillez entrez une adresse e-mail valide";
			case 5:
				break;
		}
		
		return errorMsg;
	}
	
	private static String getErrorMsgForConfirmEmail(ResultValidation resValid) {
		String errorMsg = "";
		
		switch(resValid.getCode()) {
			case 1:
				errorMsg += "Vous devez remplir le champ de confirmation pour l'adresse email.\n";
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				errorMsg += "L'adresse email ne correspond pas avec la confirmation de l'adresse email.";
				break;
		}
		
		return errorMsg;
	}
	
	private static String getErrorMsgForPassword(ResultValidation resValid) {
		String errorMsg = "";
		Restriction restriction = resValid.getRestriction();
		
		switch(resValid.getCode()) {
			case 1:
				errorMsg += "Vous devez remplir le champ mot de passe.\n";
			case 2:
			case 3:
				errorMsg += "Vous devez saisir entre "+restriction.getMinLength()+ " et "+restriction.getMaxLength()+" caractère(s).\n";
			case 4:
				errorMsg += "Veuillez entrez une mot de passe comprenand au moins 1 minuscule, 1 majuscule, 1 chiffre et 1 caractère spécial.";
			case 5:
				break;
		}
		
		return errorMsg;
	}
	
	private static String getErrorMsgForConfirmPassword(ResultValidation resValid) {
		String errorMsg = "";
		
		switch(resValid.getCode()) {
			case 1:
				errorMsg += "Vous devez remplir le champ de confirmation pour le mot de passe.\n";
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				errorMsg += "Le mot de passe ne correspond pas avec la confirmation du mot de passe.";
				break;
		}
		
		return errorMsg;
	}
	
}
