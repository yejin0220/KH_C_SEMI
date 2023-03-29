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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<title>로그인 페이지</title>
<style>
     * {
            padding: 0;
            margin: 0;
            border: 1px;
            font-family: 'Noto Sans KR', sans-serif;
        }

        body {
            font-size: 14px;
        }

        .all {
            position: relative;
        }

        .login_logo>img {
            width: 300px;
            height: 200px;
            position: absolute;
            bottom: 348px;
            left: 700px;
        }

        .login-wrapper {
            width: 500px;
            height: 350px;
            padding: 60px;
            box-sizing: border-box;
            margin-left: 600px;
            margin-top: 200px;
        }

        .login-wrapper>h2 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        #login-form>input {
            width: 100%;
            height: 48px;
            padding: 0 10px;
            box-sizing: border-box;
            margin-bottom: 16px;
            border-radius: 6px;
            background-color: rgba(224, 224, 224, 0.34);
        }

        #login-form>input::placeholder {
            color: rgb(158, 158, 158);
        }

        #login-form>input[type="submit"] {
            color: #fff;
            font-size: 16px;
            background-color: rgba(52, 152, 219, 0.84);
            margin-top: 20px;
            cursor: pointer;
        }

        #login-form>input[type="checkbox"] {
            display: none;
        }

        #login-form input[type="checkbox"]+label {
            cursor: pointer;
            padding-left: 26px;
            background-image: url("checkbox.png");
            background-repeat: no-repeat;
            background-size: contain;
        }

        #login-form input[type="checkbox"]:checked+label {
            background-image: url("checkbox-active.png");
            background-repeat: no-repeat;
            background-size: contain;
        }

        #login-form button {
            margin-bottom: 13px;
            cursor: pointer;
        }

        .separator {
            border-top: 1px solid #777;
            margin-top: 12px;
            margin-bottom: 20px;
        }

        .sign_btn>button {
            color: rgba(51, 51, 51, 0.76);
            background-color: rgba(224, 224, 224, 0.34);
            width: 90px;
            height: 30px;
            cursor: pointer;
        }

        .sign_btn{
            margin-left: 115px;
        }

        .id_search * {
            text-decoration: none;
            font-size: 13px;
            color: rgba(51, 51, 51, 0.76)
        }

        .id_search{
            margin-top: 6px;
        }

        .login_footer{
            display: inline-flex;
        }
</style>
</head>
<body>
  <div class="all">
        <div class="login_logo">
            <img src="resources/logo.png" width="200px">
            <div class="login-wrapper">
                <h2>로그인</h2>
                <form method="post" action="<%= contextPath %>/login.me" id="login-form">
                    <input type="text" name="userId" placeholder="아이디" required>
                    <input type="password" name="userPwd" placeholder="비밀번호" required>
                    <label for="remember-check">
                        <input type="checkbox" id="remember-check"> 아이디 저장
                    </label>
                    <input type="submit" value="로그인">

                    <!-- naver button -->
                    <button class="naver__btn">
                        <img src="<%=contextPath %>/resources/네이버 로그인.png" alt="" width="380" height="50">
                    </button>
                    <br>
                    <!-- kakao button -->
                    <button class="kakao__btn">
                        <img src="<%=contextPath %>/resources/kakao_login_large_wide.png" alt="" width="380" height="50">
                    </button>
                </form>

                <div class="separator"></div>
                <div class="login_footer">
                    <div class="id_search">
                        <a href="">아이디 찾기</a>
                        <span>ㅣ</span>
                        <a href="">비밀번호 찾기</a>
                    </div>
                    <div class="sign_btn" align="end">
                        <button type="submit" disabled;>회원가입</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>