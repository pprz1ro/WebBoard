<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile registration</title>
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
	width: 290px;
	background: #755139;
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
	flex-direction: column;
}

.border {
	align-items: center;
	justify-content: center;
}

.wall {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
}

form .border .input-box {
	width: calc(100%/ 2 -15px);
	margin: 20px 0;
}

form .border .input-box input {
	width: 40%;
	height: 20px;
	background: #E7E7E7;
	color: #252525;
	outline: none;
	border-radius: 10px;
	border: 1px solid rgba(255, 255, 255, 1);
	padding-Left: 17px;
	font-size: 1rem;
	transition: all .40s ease;
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

::placeholder {
	font-size: 10px;
}
</style>
</head>
<body>
	<div class="boardwall">
		<div class="title">Profile Registration</div>
		<br />
		<form action="/WebBoard1126/Regicheck" method="post">
			<div class="border">
				<!-- プロフィール入力項目 -->
				<div class="input-box">
					<span class="text">ID: </span> <input type="text" name="id"
						size="10" placeholder="※6～12文字" class="inputbox" />
				</div>

				<div class="input-box">
					<span class="text">password: </span> <input type="password"
						name="password" size="10" placeholder="※6～12文字" class="inputbox" />
				</div>

				<div class="input-box">
					<span class="text">ユーザー名: </span> <input type="text" name="name"
						size="10" placeholder="※10文字以内" class="inputbox" />
				</div>

				<div class="input-box">
					<span class="text">年齢: </span> <input type="text" name="age"
						size="10" placeholder="※3桁以内" class="inputbox" />
				</div>


				<span class="text">性別: 男<input type="radio" name="gender"
					value="男" /> 女<input type="radio" name="gender" value="女" />
				</span>


				<div class="input-box">
					<span class="text">自己紹介: </span>
					<textarea name="selfinf" rows="5" cols="50" maxlength="400"
						placeholder="※400文字以内" class="inputbox"></textarea>
				</div>

			</div>

			<div class="wall">
				<input type="submit" value="登録する" class="btn" /> 
				<input type="reset" value="クリア" class="btn" />
			</div>
		</form>

		<form action="/WebBoard1126/Board.jsp" method="post">
			<input type="submit" value="掲示板へ" class="btn" />
		</form>

	</div>
</body>
</html>
