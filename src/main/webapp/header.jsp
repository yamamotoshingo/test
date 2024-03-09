<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- ユーザ情報を取得するためにLoginUserBeanクラスを宣言する --%>
<jsp:useBean id="user_db" scope="session"
	class="ShoppingSite.LoginUserBean" />

<%-- Java入門 ヘッダー画面 --%>
<div align="right" class="header">
	<%-- ログイン済みの場合は名前を表示 --%>
	<div class="loginuser">
		ようこそ「<jsp:getProperty property="name" name="user_db" />」さん！
	</div>
	<%-- パラメータ名「submit」で履歴およびログアウトを判定する --%>
	<a href="/ShoppingSite/LoginServletShopping?submit=履歴" class="btn">購入履歴</a>
	<a href="/ShoppingSite/LoginServletShopping?submit=ログアウト" class="btn">ログアウト</a>
</div>