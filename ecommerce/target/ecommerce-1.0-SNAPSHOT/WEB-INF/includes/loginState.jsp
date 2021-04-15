<%@page import="util.Const"%>
<%@page import="manager.MLogin"%>
<%@page import="action.ActionLogin"%>
<%@page import="manager.MCookies"%>
<%@ page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
User user = (User)session.getAttribute("user");


if(user == null && request.getAttribute("logout") == null) {
	user = ActionLogin.getUserFromAutoLogin(request);
	session.setAttribute("user", user);
}

if(user != null) {%>
<li id="loginState">
	<a href="#" id="user"><%=user.getName()%></a>
	         
		<li>&nbsp;</li>
		<li><a href="login">Déconnexion</a></li>
	
</li>
<% }
else
{
	
%>
<li><a href="signup<%=(request.getParameter("fromCart") != null ? "?fromCart=true" : "")%>">S'enregistrer</a></li>
<li id="loginState"><a href="login">Connexion</a></li>
<%	
}
%>