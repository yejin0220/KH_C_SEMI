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
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
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
            left: 649px;
        }

        .login-wrapper {
            width: 500px;
            height: 350px;
            padding: 60px;
            box-sizing: border-box;
            margin-left: 550px;
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
        
        input:focus {
            outline: none;
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
                    <input type="text" name="userId" placeholder="아이디" autocomplete="off" required>
                    <input type="password" name="userPwd" placeholder="비밀번호" autocomplete="off" required>
                    <label for="remember-check">
                        <input type="checkbox" id="remember-check" > 아이디 저장
                    </label>
                    <input type="submit" onclick="submitLogin();" value="로그인" >

                <div class="separator"></div>
                
                <div class="login_footer">
                
                    <div class="id_search">
                        <a href="">아이디 찾기</a>
                        <span>ㅣ</span>
                        <a href="">비밀번호 찾기</a>
                    </div>
                    
                    <div class="sign_btn" align="end">
                        <button type="button" onclick="enrollPage();">회원가입</button>
                </form>
                    </div>
                </div>
                <script>
         function enrollPage(){
            location.href = "<%= contextPath%>/views/member/memberEnroll.jsp"; 
         }
         function submitLogin(){
            
            let userId = $("#login-form input[name=userId]").val();
            
            if($("#remember-check").is(":checked") ) {
             document.cookie = "remember-check="+userId+"; path=/; max-age="+60*60*24;     
            }else{
               document.cookie = "remember-check=; path=/; max-age=0;"
            }
            
            
            $("#login-form").submit();
            
         }
         
         function getCookie(){
            let value = "";
            if(document.cookie.length > 0){
               let index = document.cookie.indexOf("remember-check=");
               if(index != -1){ 
                  index += "remember-check=".length;
                  let end = document.cookie.indexOf(";",index);
                  
                  if(end == -1){
                     value = document.cookie.substring(index);
                  }else{
                     value = document.cookie.substring(index,end );
                  }
                  $("#login-form input[name=userId]").val(value);
                  $("#remember-check").attr("checked",true);
               }
               
            }
         }
         $(function(){
            getCookie();
         });
      </script>
            </div>
        </div>
    </div>
</body>
</html>