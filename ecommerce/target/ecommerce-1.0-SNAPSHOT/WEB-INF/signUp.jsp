<%@page import="util.Misc"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>
<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<%
@SuppressWarnings("unchecked")
HashMap<String, String> hm_formParamValue = (HashMap<String, String>)request.getAttribute("hm_formParamValue");
@SuppressWarnings("unchecked")
HashMap<String, String> hm_fieldErrorMsg = (HashMap<String, String>)request.getAttribute("hm_fieldErrorMsg");
String error = (String)request.getAttribute("error");
%>
<!-- Page Content -->
<div class="container">
 <%
 if(error != null) {
	 if(error.equals("accountExisting")) {
%>
		<div class="alert alert-info">
			Un compte existe déjà pour cette adresse email.
		</div>
<%
	 }
	 else if(error.equals("DBProblem")) {
%>
			<div class="alert alert-danger">
				Une erreur de connexion c'est produite. Veuillez attendre quelques temps avant de faire une nouvelle tentative.
				Si vous voyez ce message pour la deuxième fois, veuillez contactez l'administrateur du site pour lui informer du
				problème.
			</div>
<%
	 }
 }
 %>
    
	  	<form action="signup" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
	  		<div class="panel-heading">
	  			<h3 class="panel-title">Inscription</h3>
	  		</div>
	  		<div class="panel-body">
		  		<fieldset class="col-sm-6 col-lg-6 col-md-6">
		  			<legend>Informations</legend>
<%
if(hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("name")) {
%>
					<div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("name")%></div>
<%                                      
}
%>
					<div class="form-group">
						<div class="col-sm-10">
							<label for="lastName" class="col-sm-2 control-label">*Nom</label>
							<input type="text" id="lastName" class="form-control" name="name" value="<%=Misc.getOrDefault(hm_formParamValue, "name", "")%>" required />
						</div>
					</div>
<%

if(hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("email")) {
%>
					<div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("email")%></div>
<%
}
%>
					<div class="form-group">	
						<div class="col-sm-10">
							<label for="email" class="col-sm-2 control-label">*Email</label>
							<input type="email" id="email" class="form-control" name="email" placeholder="Email" value="<%=Misc.getOrDefault(hm_formParamValue, "email", "")%>" required />
						</div>
					</div>
<%
if(hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("confirmEmail")) {
%>
					<div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("confirmEmail")%></div>
<%
}
%>
					<div class="form-group">	
						<div class="col-sm-10">
							<label for="confirmEmail" class="col-sm-2 control-label" style="padding-top: 0px;">(Confirmation)<br />*Email</label>
							<input type="email" id="confirmEmail" class="form-control" name="confirmEmail" placeholder="Email" value="<%=Misc.getOrDefault(hm_formParamValue, "confirmEmail", "")%>" required />
						</div>
					</div>
<%
if(hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("password")) {
%>
					<div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("password")%></div>
<%
}
%>
					<div class="form-group">
						<div class="col-sm-10">
							<label for="password" class="col-sm-2 control-label">*Mot&nbsp;de&nbsp;passe</label>
							<input type="password" id="password" class="form-control" name="password" required />
						</div>
					</div>
<%
if(hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("confirmPassword")) {
%>
					<div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("confirmPassword")%></div>
<%
}
%>
					<div class="form-group">
						<div class="col-sm-10">
							<label for="confirmPassword" class="col-sm-2 control-label" style="padding-top: 0px;">(Confirmation)<br />*Mot&nbsp;de&nbsp;passe</label>
							<input type="password" id="confirmPassword" class="form-control" name="confirmPassword" required />
						</div>
					</div>
				</fieldset>
				
				<%
				if(request.getParameter("fromCart") != null){
				%>
				<input type="hidden" name="fromCart" value="true">
				<%
					}
				%>
				<div class="form-group text-center" style="clear: left; top: 15px; margin-bottom: 15px;">
						<button type="submit" class="btn btn-default">S'enregistrer</button>
				</div>
			</div>
		</form>
    </div>
<!-- Footer -->
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>
