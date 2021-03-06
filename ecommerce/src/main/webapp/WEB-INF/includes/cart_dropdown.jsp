<%@page import="manager.MSession"%>
<%@page import="util.Const"%>
<%@page import="entities.ItemCart"%>
<%@page import="entities.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    
    HttpSession sessionUser = MSession.getSession(request);	
		if (sessionUser.getAttribute("cart") == null){
			Cart cart = new Cart();
			sessionUser.setAttribute("cart", cart);
		}else{
			Cart cart = (Cart) sessionUser.getAttribute("cart");			
			for (ItemCart itemC : cart.getCart().values()) {
				if(itemC.getName() == null){
					cart = new Cart();
					sessionUser.setAttribute("cart", cart);
					break;
				}
			}
		}       
	
	Cart cart = (Cart)session.getAttribute("cart");
	
%>
    
<li class="dropdown">
        
	<button type="submit" class="btn btn-default dropdown-toggle" data-toggle="dropdown"  aria-expanded="false">
                <a href="cart" style="text-decoration: none">
		<span class="glyphicon glyphicon-shopping-cart"></span>
		<span id="nb_cart_item"><%=cart.getSize()%></span> - Items
                <span class="caret"></span></a></button>
	<ul class="dropdown-menu dropdown-cart" role="menu">
		<%
		if(cart != null && cart.getSize() > 0){
				for (ItemCart itemC : cart.getCart().values()) {	
		%>
			<li>
			    <span class="item">
					<span class="item-left">
					    <img src="<%=itemC.getImage()%>" alt="miniature produit" />
					    <span class="item-info">
					        <span><a href="item?item=<%=itemC.getId()%>"><%=itemC.getName()%></a></span>
					        <span><%=itemC.getPrice()%>$ (<%=itemC.getQty()%>)</span>
					    </span>
					</span>
					<span class="item-right">
					    <button name="cart-dropdown-remove" data="<%=itemC.getName()%>" class="btn btn-xs pull-right remove-cart-item">x</button>
					</span>
				</span>
			</li>
	    <%
			}
		%>
			<li class="divider"></li>
	      
	     	<li id="dd-info" class="text-center"><a href="cart">Commander</a></li>
		<%
		}else{
			%>
				<li id="dd-info" class="text-center">
					<span>Le panier est vide</span>
				</li>
			<%
		}
	    %>
	</ul>
</li>