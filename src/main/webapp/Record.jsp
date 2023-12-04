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
<title>Comment History</title>
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
	width: 260px;
	background: #755139;
}

.border {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
}

.inputbox {
	height: 30px;
}

.cent {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
	border-bottom: 1px solid black;
	pading: 1px;
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
	margin: 10px;
}

.btn:hover {
	box-shadow: none;
	color: #000000;
	background: #E7E7E7;
	border-radius: 5%;
}

.delete {
	opacity: 0.8;
	background-color: #9F9F9F;
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
</style>
</head>
<body>
	<div class="boardwall">
		<div class="title">Comment History</div>
		<%
		String sql5 = null;
		String id = null;
		String name = null;
		/*この文で作成済みのsessionを取得 */
		if (session.getAttribute("seid") != null) {
			id = (String) session.getAttribute("seid");
			name = (String) session.getAttribute("sename");
		}
		%>

		<h1>
			ようこそ<%=name%>さん
		</h1>

		<!-- プロフィールへ戻る、ログアウトボタンを配置 -->
		<form action="Profile.jsp" method="get">
			<input type="submit" value="プロフィールへ戻る" class="btn" />
		</form>

		<form action="<%=request.getContextPath()%>/Logout" method="get">
			<input type="submit" value="ログアウトする" class="btn" />
		</form>

		</br>

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
			rs = stmt.executeQuery("select * from written where id='" + id + "'");
			int num = 0;
			// 得られた結果をレコードごとに表示
			while (rs.next()) {
				num = num + 1;
				String nowtime = (String) rs.getString("nowtime");
		%>

		<%-- 投稿内容を表示 --%>
		<div class="border">
			<form action="<%=request.getContextPath()%>/Deletecom" method="get">
				<%=num%>.
				<%=rs.getString("name")%>
				<%=nowtime%>
				<input type="hidden" id="time" name="nowtime" value="<%=nowtime%>" />
				<input type="submit" value="削除" name="now" class="delete" />
			</form>
		</div>

		<div class="cent">
			<%=rs.getString("written")%>
		</div>

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

	</div>

</body>
</html>