<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
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
	width: 170px;
	background: #755139;
}

.inputbox {
	width: 85%;
	height: 20px;
	border-bottom: 2px solid black;
	margin: 2px 0;
	background: #E7E7E7;
	color: #252525;
	outline: none;
	border-radius: 10px;
	border: 1px solid rgba(255, 255, 255, 1);
}

.border {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
}

.boardwall {
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 5px;
	background: transparent;
	border: 2px solid rgba(255, 255, 255, 5);
	border-radius: 20px;
	backdrop-filter: blur(20px);
	box-shadow: 0 0 30px rgba(0, 0, 0, 5);
	display: flex;
	flex-direction: column;
}

.p1 {
	text-align: center;
	position: relative;
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
</style>
</head>
<body>
	<div class="boardwall">
		<div class="title">Login Form</div>
		<br />
		<!-- ログインボタン、プロフィール登録ボタン、掲示板ボタン配置 -->
		<form action="<%=request.getContextPath()%>/Loginresult" method="post">
			<div class="input-box">
				<p>ID ※半角英数字6～12文字</p>
				<input type="text" name="id" class="inputbox">
				<p>パスワード ※半角英数字6～12文字</p>
				<input type="password" name="password" class="inputbox">
				<div class="border">
					<input type="submit" value="ログインする" class="btn">
				</div>
			</div>
		</form>

		<form action="Register.jsp" method="get">
			<p>ユーザー登録がお済みでない方</p>
			<input type="submit" value="ID・パスワード・プロフィール登録へ" class="btn" />
		</form>

		<form action="Board.jsp" method="post">
			<input type="submit" value="掲示板へ" class="btn">
		</form>
	</div>
</body>
</html>