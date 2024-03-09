<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ShoppingSite.ItemBean"%>

<%-- Java入門 購入確認画面 --%>
<!DOCTYPE html>
<html>
	<head>
	    
		<title>Java入門</title>
		<link href="./css/confirm.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="./javascript/shopping.js" charset="UTF-8"></script>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h1>購入確認</h1>
		<div class="form_all">
		<form action="./ResultServlet" method="post" id="back">
		    <%
		      ArrayList<ItemBean> itembeanList = (ArrayList<ItemBean>)request.getAttribute("itemlist");
			  ArrayList<String> quantityList = (ArrayList<String>)request.getAttribute("quantitylist");
			%>
		    <div class="button">
		      <input class="common_button" type="submit" value="購入する" id= "purchase" onclick="purchaseConfirm();">
		      <p class="purchase_date">購入日：<%=request.getAttribute("purchasedate") %></p>
		      <input type="hidden" name="item_purchasedate" value="<%=request.getAttribute("purchasedate") %>"/>
		    </div>
		    <div class="confirm_form">
			<table class="table_list">
				<tbody>
					<tr>
						<th>商品ID</th>
						<th>商品画像</th>
						<th>商品名</th>
						<th>価格</th>
						<th>在庫数</th>
						<th>購入数</th>
					</tr>
					 <% for (int i = 0; i < itembeanList.size(); i++) { %>
					    <tr>
						  <td><%=itembeanList.get(i).getItemId() %></td>
						  <td><img src="<%=itembeanList.get(i).getImagePath() %>" class="image"></td>
						  <td><%=itembeanList.get(i).getItemName() %></td>
						  <td class="int"><%=itembeanList.get(i).getPrice() %></td>
						  <td class="int"><%=itembeanList.get(i).getQuantity() %></td>
						  <td class="int"><%=quantityList.get(i) %></td>
						  <%-- hiddenでパラメータをセットしておく --%>
						  <%-- 購入処理を行うため、hidden（画面には表示されない情報）に商品IDと購入数を設定しておく --%>
						  <input type="hidden" name="item_id" value="<%=itembeanList.get(i).getItemId() %>">
						  <input type="hidden" name="item_quantity" value="<%=quantityList.get(i) %>">
						</tr>
						<% } %>
				</tbody>
			</table>
			</div>
		</form>
		<form action="./ShoppingServlet" method="post" class="back">
			<input class="common_button" type="submit" value="一覧に戻る">
		</form>
		</div>
	</body>
</html>