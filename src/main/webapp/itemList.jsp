<%@ page import="java.util.ArrayList"%>
<%@ page import="ShoppingSite.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- Java入門 商品一覧画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="./css/itemList.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="./javascript/shopping.js" charset="UTF-8"></script>
	</head>
	<body>
		<%-- jsp:includeでヘッダー画面を読み込む --%>
		<jsp:include page="/header.jsp"/>
		<h1>商品一覧</h1>
		<%-- リクエストスコープからBeanクラスの配列を取得（eclipseの警告が出ても今回は大丈夫です） --%>
		<% ArrayList<ItemBean> itemList = (ArrayList<ItemBean>)request.getAttribute("itemList"); %>
		<div class="form">
		<form action="/ShoppingSite/BuyItemServlet">
		    <div class="purchase_button">
		      <input class="back_button" type="button" onclick="location.href='./login.jsp'" value="戻る">
			  <input class="common_button" type="submit" value="購入" name="purchase" id="button" disabled="true"/>
			  <input class="delete_button" type="button" onclick="onDeleteCheck();" value="取り消し">
			</div>
			<table class="table_list">
				<tbody>
					<tr>
					    <th class="checkbox"></th>
						<th>商品ID</th>
						<th>商品画像</th>
						<th>商品名</th>
						<th>価格</th>
						<th>在庫数</th>
						<th>数量</th>
					</tr>
					
					<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
					<% for(ItemBean bean : itemList) { %>
					<tr>
					    <% if(bean.getQuantity() == 0) { %>
					    <td class="checkbox">
					      <input type="checkbox" name="item" value="<%=bean.getItemId() %>" onclick="onPurchaseButton();" id="noitem" disabled="true">
					    </td>
					    <% } else { %>
					    <td class="checkbox">
					      <input type="checkbox" name="item" value="<%=bean.getItemId() %>" onclick="onPurchaseButton();" id="<%=bean.getItemId() %>">
					    </td>
					    <% } %>
						<%-- 商品ID --%>
						<td><%= bean.getItemId() %></td>
						<%-- 商品画像 --%>
						<td><img src="<%=bean.getImagePath() %>" class="image"></td>
						<%-- 商品名 --%>
						<td><%= bean.getItemName() %></td>
						<%-- 価格 --%>
						<td class="int"><%= bean.getPrice() %></td>
						<%-- 数量（在庫） --%>
						<td class="int"><%= bean.getQuantity() %></td>
						
						<%-- 在庫が0の場合はリストボックスと購入ボタンを表示しない処理を入れる --%>
						<% if(bean.getQuantity() != 0) { %>
						<td>
						    <select class="list" name="<%= bean.getItemId() %>list" onchange="onSelectChange('<%= bean.getItemId() %>');">
						        <% for(int i = 0; i <= bean.getQuantity(); i++) { %>
						          <option value="<%= i %>"><%=i %></option>
						        <% } %>
						    </select>
						</td>
						<% } else { %>
						    <td class="button">売り切れ</td>
						<% } %>
					</tr>
				<% } %>
				</tbody>
			</table>
		</form>
		</div>
	</body>
</html>