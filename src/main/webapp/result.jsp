<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- Java入門 購入結果画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="./css/result.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h1>購入結果</h1>
		<div class="result">
		  <p class="thankyou">ご購入ありがとうございました</p>
		  <img src="./image/Thankyou.jpg" class="image"/>
		</div>
		<form action="./ShoppingServlet" method="post">
			<input class="common_button" type="submit" value="一覧に戻る">
		</form>
	</body>
</html>