<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Results</title>
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
	width: 200px;
	background: #755139;
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

.border2 {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
}

.caution {
	font-size: 10px margin-top: 10px;
}
</style>
</head>
<body>
	<div class="boardwall">

		<div class="caution">※本当に削除しますか?</div>
		<div class="border2">
			<!-- プロフィール削除、プロフィールへ戻るためのボタンを配置 -->
			<form action="/WebBoard1126/Regidelete" method="post">
				<input type="submit" value="削除する" class="btn" />
			</form>

			<form action="/WebBoard1126/Profile.jsp" method="post">
				<input type="submit" value="プロフィールへ戻る" class="btn" />
			</form>
		</div>

	</div>
</body>
</html>