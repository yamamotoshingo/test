<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collections"%>
<%@ page import="ShoppingSite.HistoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="user_db" scope="session" class="ShoppingSite.LoginUserBean"/>

<%-- Java入門 購入履歴画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="./css/history.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h1><jsp:getProperty property="name" name="user_db"/>さんの購入履歴</h1>
		<%-- リクエストスコープからBeanクラスの配列を取得（eclipseの警告が出ても今回は大丈夫です） --%>
		<% 
		  ArrayList<HistoryBean> historyList = (ArrayList<HistoryBean>)request.getAttribute("history");
		  Collections.reverse(historyList);
		%>
		<form action="./ShoppingServlet" method="post">
			<input class="common_button" type="submit" value="一覧に戻る">
		</form>
		<div class="table">
		<table class="table_list">
			<tbody>
				<tr>
					<th>商品ID</th>
					<th>商品画像</th>
					<th>商品名</th>
					<th>購入数</th>
					<th>購入日</th>
				</tr>
				<%-- リクエストスコープから表示する値を取得 --%>
				<% for(HistoryBean bean : historyList) { %>
					<tr>
						<%-- 商品ID --%>
						<td><%= bean.getItemId() %></td>
						<%-- 商品画像 --%>
						<td><img src="<%= bean.getImagePath() %>" class="image"></td>
						<%-- 商品名 --%>
						<td><%= bean.getItemName() %></td>
						<%-- 数量（在庫） --%>
						<td class="int"><%= bean.getQuantity() %></td>
						<%-- 購入日 --%>
						<td><%= bean.getPurchaseDate() %></td>
					</tr>
				<% } %>
			</tbody>
		</table>
		</div>
	</body>
</html>