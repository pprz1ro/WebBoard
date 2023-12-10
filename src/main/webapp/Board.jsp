<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%!
	public void jspInit() {
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
<title>Board</title>
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
	width: 100px;
	background: #755139;
}

.cent {
	display: flex;
	justify-content: center;
	width: 100%;
}

.border {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
	border-bottom: 1px solid black;
	padding: 1px;
}

.inputbox {
	height: 30px;
	border-bottom: 2px solid black;
}

.btn {
	display: inline-block;
	border-radius: 5%;
	font-size: 10px;
	text-align: center;
	cursor: pointer;
	padding: 12px 12px;
	background: #E7E7E7;
	color: #000000;
	line-height: 1em;
	transition: .3s;
	box-shadow: 6px 6px 3px #666666;
	border: 2px solid #000000;
    margin:10px;
}

.btn:hover {
	box-shadow: none;
	color: #000000;
	background: #E7E7E7;
	border-radius: 5%;
}

.wall {
	padding: 5px;
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

.caution {
	font-size: 10px margin-top: 10px;
}
</style>
</head>
<body>
	<div class="boardwall">
		<div class="title">Board</div>
		<%
		String sql5 = null;
		String id = null;
		String name = null;
		/* この文で作成済みのsessionを取得 */
		if (session.getAttribute("seid") != null) {
			id = (String) session.getAttribute("seid");
			name = (String) session.getAttribute("sename");
		}

		/* sessionを取得できない場合、ログインボタンとプロフィール登録ボタンを配置 */
		if (id == null) {
		%>
		<div class="wall">
			<form action="Login.jsp" method="get">
				<input type="submit" value="ログインする" class="btn" />
			</form>
		</div>

		<div class="wall">
			<form action="Register.jsp" method="get">
				<input type="submit" value="ID・パスワード・プロフィール登録へ" class="btn" />
			</form>
		</div>
		</br>

		<%
		/* sessionを取得できた場合、プロフィールボタンを配置 */
		} else if (id != null) {
		%>
		<h1>
			ようこそ<%=name%>さん
		</h1>
		<form action="Profile.jsp" method="get">
			<input type="submit" value="プロフィールへ" class="btn" />
		</form>
		</br>

		<%
		}
		%>


		<%
		/* データベースへのアクセス開始 */
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			/*データベースに接続するConnectionオブジェクトの取得 */
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/[DBテーブル名]", "[MySQLユーザー名]", "MySQLパスワード]");
			/* データベース操作を行うためのStatementオブジェクトの取得 */
			stmt = con.createStatement();
			/* SQL()を実行して、結果を得る */
			rs = stmt.executeQuery("select * from written ");
			int num = 0;
			/* 得られた結果をレコードごとに表示*/
			while (rs.next()) {
				num = num + 1;
		%>

		<tr>
			<div class="cent">
				<%=num%>.
				<%=rs.getString("name")%>
				<%=rs.getString("nowtime")%>
			</div>

			<div class="border">
				<%=rs.getString("written")%>
			</div>
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
		
		<!-- コメント送信欄と送信ボタン配置 -->
		<h2>コメント送信フォーム</h2>
		<div class="wall">
			<form action="<%=request.getContextPath()%>/Comwrite" method="post">
				コメント：<input type="text" name="comment" class="inputbox"> <input
					type="submit" value="書き込み" class="btn">
			</form>
			<div class="caution">※コメントの削除機能をご利用の方はアカウント登録が必要です。</div>
		</div>
	</div>
	</h1>
</body>
</html>
