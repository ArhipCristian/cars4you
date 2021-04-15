<%-- 
    Document   : listCategories
    Created on : Jan 19, 2021, 9:20:26 AM
    Author     : cdjipsum
--%>

<%@page import="entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>

<% ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catégories de voitures</title>
    </head>
    <body>        
       
        <jsp:include page="<%=Const.PATH_MENU_JSP%>" />
        <div class="container">
            <% if (categories.size() > 0) { %>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Order</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <% for (Category category : categories) { %>
                        <tr>
                            <td><%=category.getName()%></td>
                            <td>
                                <form method="GET" action="editCategory">
                                    <input type="hidden" name="category" value="<%= category.getId() %>"/>
                                    <button type="submit">Editer</button> 
                                </form> 
                            </td>
                        </tr>
                    <% } %>
                </table>
            <% } else { %>
                <div class="alert alert-info">
			Aucune categorie pour le moment.
		</div>
            <% } %>
        </div>
        <jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>
        
    </body>
</html>
