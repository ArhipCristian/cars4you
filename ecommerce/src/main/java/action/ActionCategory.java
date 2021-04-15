package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MCategory;

public class ActionCategory {
	
	public static void getCategories(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("categories", MCategory.getCategories());
	}
	
	public static int getSelectedCategory(HttpServletRequest request, HttpServletResponse response) {
		
		String paramCategory = request.getParameter("category");		
		
		int categorySelected;

		if(paramCategory != null) {
			try {				
				categorySelected = Integer.valueOf(paramCategory);
				if(MCategory.isExist(categorySelected) != 0)
					categorySelected = 1;
			}
			catch(NumberFormatException e) {
				categorySelected = 1;
			}
		}
		else
			categorySelected = 5;
		
		return categorySelected;
	}
}
