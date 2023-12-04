<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%!public void jspInit() {
		try {
			/* JDBCドライバをロード */
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<!-- デザイン付与 -->
<style>
body {
	background-color: #9F9F9F;
}

.title {
	position: relative;
	font-size: 30px;
	font-weight: 700;
	color: black;
	margin-bottom: 10px;
}

.title::before {
	position: absolute;
	content: '';
	Left: 0;
	bottom: -7px;
	height: 3px;
	width: 110px;
	background: #755139;
}

.btn {
	display: inline-block;
	border-radius: 5%; /* 角丸       */
	font-size: 10px; /* 文字サイズ */
	text-align: center; /* 文字位置   */
	cursor: pointer; /* カーソル   */
	padding: 12px 12px; /* 余白       */
	background: #E7E7E7; /* 背景色     */
	color: #000000; /* 文字色     */
	line-height: 1em; /* 1行の高さ  */
	transition: .3s; /* なめらか変化 */
	box-shadow: 6px 6px 3px #666666; /* 影の設定 */
	border: 2px solid #000000; /* 枠の指定 */
	margin: 10px;
}

.btn:hover {
	box-shadow: none; /* カーソル時の影消去 */
	color: #000000; /* 背景色     */
	background: #E7E7E7; /* 文字色     */
	border-radius: 5%; /* 角丸       */
}

.boardwall {
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 20px;
	background: transparent;
	border: 2px solid rgba(255, 255, 255, 5);
	border-radius: 20px;
	backdrop-filter: blur(20px);
	box-shadow: 0 0 30px rgba(0, 0, 0, 5);
	display: flex;
	flex-direction: column;
}
.wall {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
}
</style>
</head>
<body>
	<div class="boardwall">
		<div class="title">Profile</div>

		<%
		/* この文で作成済みのsessionを取得 */
		String id = (String) session.getAttribute("seid");
		String name = (String) session.getAttribute("sename");
		%>
		<h1>
			ようこそ<%=name%>さん
		</h1>

		<%
		/* データベースへのアクセス開始 */
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			/* データベースに接続するConnectionオブジェクトの取得 */
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "sample", "Ppr0@0905");

			/* データベース操作を行うためのStatementオブジェクトの取得 */
			stmt = con.createStatement();
			/* SQL()を実行して、結果を得る */
			rs = stmt.executeQuery("select * from member WHERE id =" + "'" + id + "'");
			/* 得られた結果をレコードごとに表示 */
			while (rs.next()) {
		%>
		<tr>
			ユーザーネーム:
			<td><%=rs.getString("name")%></td>
			<br />
			年齢:
			<td><%=rs.getInt("age")%></td>
			<br />
			性別:
			<td><%=rs.getString("gender")%></td>
			<br /> 自己紹介:
			<td><%=rs.getString("about")%></td>
			<br />
		</tr>
		<%
		}

		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		/* データベースとの接続をクローズ */
		try {
		rs.close();
		} catch (Exception e) {
		}
		try {
		stmt.close();
		} catch (Exception e) {
		}
		try {
		con.close();
		} catch (Exception e) {
		}
		}
		%>

		<!-- コメント履歴、掲示板、ログアウト、アカウント削除ボタン配置 -->
		<div class="wall">
		<form action="<%=request.getContextPath()%>/Record.jsp" method="post">
			<input type="submit" value="コメント履歴へ" class="btn">
		</form>

		<form action="<%=request.getContextPath()%>/Board.jsp" method="post">
			<input type="submit" value="掲示板へ" class="btn">
		</form>
		</div>

		<form action="<%=request.getContextPath()%>/Logout" method="post">
			<input type="submit" value="ログアウトする" class="btn">
		</form>

		<form action="<%=request.getContextPath()%>/Rgdelete.jsp"
			method="post">
			<input type="submit" value="アカウントを削除する" class="btn">
		</form>

	</div>
</body>
</html>