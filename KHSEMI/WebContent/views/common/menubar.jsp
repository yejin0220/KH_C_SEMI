<%@ page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	Member loginUser = (Member) request.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴바</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}

.nav-area {
	background-color: #E6F2FF;
	align-items: center;
	margin: auto;
}

.menu {
	display: table-cell;
	height: 80px;
	width: 250px;
}

#nav {
	color: #FFD133;
	font-size: 20px;
}

.menu a:hover {
	font-weight: bold;
}

.logo {
	display: flex;
	justify-content: center;
	margin-left: 250px;
}

.login-area>ul>li a {
	color: gray;
	font-weight: bold;
}

.after-login {
	border: 1px solid black;
	visibility: hidden;
	width: 250px;
	height: 100px;
	float: right;
	margin-top: 30px;
	margin-right: 10px;
}

.after-login>table tr, .after-login>table td {
	padding-top: 5px;
}
</style>
</head>
<body>

	<div class="login-area">
		<% if (loginUser == null) { %>
			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link" href="<%= contextPath %>/login.me">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
			</ul>
		<% } else { %>
			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link" href="#">로그아웃</a></li>
				
			</ul>
		<% } %>
			<!-- 로그인시 프로필창 -->
			<div class="after-login">
				<table>
					<tr>
						<td rowspan="3" width="100px"><img src="resources/profile_basic.png" width="80px"></td>
						<td colspan="2" width="50px" style="font-weight: bold; color: #FFD133;">닉네임</td>
					</tr>
					<tr>
						<td colspan="2">마이페이지</td>
					</tr>
					<tr>
						<td><img src="resources/notification.png" width="25px"></td>
						<td><img src="resources/chat-balloon.png" width="15px"></td>
					</tr>
				</table>
			</div>
	</div>


	<div class="logo">
		<a href=""><img src="resources/logo.png" width="200px"></a>
	</div>

	<div class="nav-area">
		<ul class="nav justify-content-center">
			<li class="nav-item"><a class="nav-link active" id="nav" aria-current="page" href="<%=contextPath%>/list.mate?currentPage=1">같이걷개</a></li>
			<li class="nav-item"><a class="nav-link" id="nav" href="#">멍냥수다</a></li>
			<li class="nav-item"><a class="nav-link" id="nav" href="#">나눔&거래</a></li>
			<li class="nav-item"><a class="nav-link disabled" id="nav" style="color: gray;">대빵용</a></li>
		</ul>
	</div>


</body>
</html>