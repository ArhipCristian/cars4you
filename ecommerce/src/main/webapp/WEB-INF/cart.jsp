<%@page import="java.util.Enumeration"%>
<%@page import="entities.ItemCart"%>
<%@page import="entities.Cart"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="util.Misc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="util.Const"%>

<%
	
	DecimalFormat df = new DecimalFormat("####0.00");	
	Cart cart = (Cart)session.getAttribute("cart");
	
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class="container">
	
    <%if ( cart != null && ! cart.isEmpty()){ %>
    	<div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
        	<form name="cart" action="cart" method="post">
	            <table class="table table-hover">
	                <thead>
	                    <tr>
	                        <th>Product</th>
	                        <th>Quantity</th>
	                        <th class="text-center">Price</th>
	                        <th class="text-center">Total</th>
	                        <th> </th>
	                    </tr>
	                </thead>
	                <tbody>
	                <%
						for (ItemCart itemC : cart.getCart().values()) {	
 	                %>
	                	
	                	<tr>
	                        <td class="col-sm-8 col-md-6">
		                        <div class="media">
                                            <a class="thumbnail pull-left image_cart" href="#"> <img class="media-object" src="<%=Const.PATH_IMG_PRODUCT+itemC.getImage()%>" style="width: 72px; height: 72px;"> </a>
		                            <div class="media-body">
		                                <h4 class="media-heading"><a href="item?item=<%=itemC.getId()%>"><%=itemC.getName() %></a></h4>		                                                                
		                            </div>
		                        </div>
	                        </td>
	                        <td class="col-sm-1 col-md-1" style="text-align: center">
	                        	<input type="number" min="1" max="999" name ="<%="qty-" + itemC.getName()%>" class="form-control qty-txt" value="<%=itemC.getQty()%>" id="<%="pq-" + itemC.getName()%>" ></td>
	                        <td class="col-sm-1 col-md-1 text-center"><strong  id="<%="pp-" + itemC.getName()%>"><%=df.format(itemC.getPrice())%>$</strong></td>
	                        <td class="col-sm-1 col-md-1 text-center"><strong  id="<%="pt-" + itemC.getName()%>"><%=df.format(itemC.getPrice() * itemC.getQty())%>$</strong></td>
	                        <td class="col-sm-1 col-md-1">
	                        	<button type="submit" name ="<%="p-del-" + itemC.getName()%>" class="btn"><span class="glyphicon glyphicon-remove"></span></button>
	                        </td>
	                    </tr>
	                <%
	                	}
 	                %>
	                   
	                    <tr>
	                        <td>   </td>
	                        <td>   </td>
	                        <td>   </td>
	                        <td><h5>Sous-total</h5></td>
	                        <td class="text-right"><h5><strong  id="cart-subtotal"><%=df.format(cart.generateSubTotal())%>$</strong></h5></td>
	                    </tr>
	                    <tr>
	                        <td>   </td>
	                        <td>   </td>
	                        <td>   </td>
	                        <td><h5>Livraison estim??e</h5></td>
	                        <td class="text-right"><h5><strong  id="cart-shipping"><%=df.format(cart.generateShipCost())%>$</strong></h5></td>
	                    </tr>
	                    <tr>
	                        <td>   </td>
	                        <td>   </td>
	                        <td>   </td>
	                        <td><h3>Total</h3></td>
	                        <td class="text-right"><h3><strong  id="cart-total"><%=df.format(cart.generateTotal())%>$</strong></h3></td>
	                    </tr>
	                    <tr>
	                        <td>   </td>
	                        <td>   </td>
	                        
	                        <td>
		                        <button type="submit" name="back" class="btn btn-default">
		                            <span class="glyphicon glyphicon-shopping-cart"></span> Continuer Achats
		                        </button>
	                        </td>
	                        <td>
		                        <button type="submit" name="refresh" class="btn btn-default">
		                            Actualiser
		                        </button>
	                        </td>
	                        <td>
		                        <button type="submit" name="checkout" class="btn btn-success">
		                            Acheter <span class="glyphicon glyphicon-play"></span>
		                        </button>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
            </form>
        </div>
    </div>
    <%
    	}else{
   	%>
    	<div class="panel panel-default">
			<div class="panel-body">
				Le panier est vide
			</div>
		</div>
   	<%
   		}
   	%>
    
</div>

<!-- Footer -->
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>