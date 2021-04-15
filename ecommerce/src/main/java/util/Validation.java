package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Validation {
	
	int qtyTest = 0,
		testPassed = 0;
	
	HashMap<String, String> hm_toValidate;
	HashMap<String, Restriction> hm_keysRestrictions;
	
	public Validation(HashMap<String, String> hm_toValidate) {
		this.hm_toValidate = hm_toValidate;
		hm_keysRestrictions = new HashMap<String, Restriction>();
	}
	
	public void addRestriction(String key, Restriction restriction) {
		hm_keysRestrictions.put(key, restriction);
		qtyTest++;
	}
	
	public ArrayList<ResultValidation> validate() {
		boolean isValidate = true;
		ArrayList<ResultValidation> resultValidations = new ArrayList<ResultValidation>();		
		
		Iterator<Entry<String, Restriction>> iterator = hm_keysRestrictions.entrySet().iterator();
		
		Map.Entry<String, Restriction> me_current;  
		
		while(isValidate && iterator.hasNext()) {
			me_current = (Map.Entry<String, Restriction>) iterator.next();
			ResultValidation tmp_resultValidation = Validation.validate(hm_toValidate, (String)me_current.getKey(), (Restriction)me_current.getValue());
		    resultValidations.add(tmp_resultValidation);
			
			if(tmp_resultValidation.getCode() == 0)
				testPassed++;
		}
		
		return resultValidations;
	}
	
	public static ResultValidation validate(HashMap<String, String> hm, String key , Restriction restriction) {
		ResultValidation result = new ResultValidation(0, key, restriction);
		
		if(!restriction.isOptionalValid() && hm.get(key) == null 
				|| (!restriction.isOptionalValid() && hm.get(key) != null && hm.get(key).length() == 0)) {
				result.setCode(1);
			}	
		
		if(result.getCode() == 0 && ((restriction.isOptionalValid() && hm.get(key) != null && hm.get(key).length() > 0) ||
				(!restriction.isOptionalValid() && hm.get(key) != null))) {			
			if(restriction.isMinLength() && hm.get(key).length() < restriction.getMinLength()) {
				result.setCode(2);
			}			
			else if(restriction.isMaxLength() && hm.get(key).length() > restriction.getMaxLength()) {
				result.setCode(3);
			}			
			else if(restriction.isPattern() && !restriction.getPattern().matcher(hm.get(key)).matches()) {
				result.setCode(4);
			}			
			else if(restriction.isString() && !restriction.getString().equals(hm.get(key))) {
				result.setCode(5);
			}
		}

		return result;
	}
	
	public boolean isValidate() {
		return (qtyTest == testPassed);
	}
}
