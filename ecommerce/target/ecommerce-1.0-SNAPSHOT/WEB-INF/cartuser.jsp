
<%@page import="entities.User"%>
<%@page import="util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	User user = (User)session.getAttribute("user");
	
%>
<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>
<div class="container">
	<div class="progress-cart">		
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading">
	    	<h3 class="panel-title">Compte</h3>
	  	</div>
	  	<div class="panel-body">
			<fieldset class="col-sm-6 col-lg-6 col-md-6">
			  <div class="panel">
			  	<legend>Information Utilisateur</legend>
			    <p>Nom: <%=user.getName()%></p>
			    <p>Email: <%=user.getEmail()%></p>
			  </div>
			</fieldset>			
		</div>
	</div>
	<form action="cartuser" method="post" style="float:left;margin-right:10px;">
	<button type="submit" name="toInvoice" class="btn btn-success">
		Confirmer <span class="glyphicon glyphicon-play"></span>
	</button>
	</form>
	<button type="submit" name="modify" class="btn btn-default">
		Modifier
	</button>
</div>
<!-- Footer -->
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>