<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="util.Const"%>

<!-- Navigation -->
<div class="container">
    
    <div class="panel panel-default">
            <div class="panel-body">
                    <a href="home"><img src="images/cars4you.jpg" /></a>    
                    <div class="navbar-right" style="margin-right:0">
                            <ul class="nav navbar-nav navbar-right">                                   
                            <jsp:include page="<%=Const.PATH_CART_DROPDOWN_JSP%>"/>
                            </ul>
                    </div>
            </div>
    </div>
    <nav class="navbar navbar-default" role="navigation">
            <div class="container">                    
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse">                           
                            <ul class="nav navbar-nav navbar-right">
                                    <jsp:include page="loginState.jsp" />
                            </ul>
                    </div>                    
            </div>
    </nav>
</div>
