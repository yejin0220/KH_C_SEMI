<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<style>
  @font-face {
		font-family: 'yg-jalnan';
		src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff') format('woff');
		font-weight: normal;
		font-style: normal;
    }
    *{
        font-family: 'Noto Sans KR', sans-serif;
    }
    .footer{
    	margin-bottom: 30px;
    }
     .footer>div{
        display: inline-block;
        vertical-align: middle;
        color: rgb(158, 158, 158);
        margin: 10px;
    }
    .footer2>div>li, .footer4>div>li{
        list-style-type: none;
        display: inline;
        margin: auto;
        text-decoration: none;
        
    }

    .footer4{
        float: right;
        margin: 30px;
    }
    .footer2>div>li a{
        color: rgb(158, 158, 158);
        padding-right: 20px;
    }
    .footer4>div>li a{
        padding-right: 9px;
    }
</style>    
</head>
<body>
	   <div class="footer">
        <div class="footer1">
            <img src="https://semiproject.s3.ap-northeast-2.amazonaws.com/%EC%9D%B4%EC%9C%A0%EC%A7%84/logo3.png" width="200px">
        </div>
    
        <div class="footer2">
            <div>
                <li><a href="">개인정보취급방침</a></li>
                <li><a href="">청소년보호정책</a></li>   
                <li><a href="">이용약관</a></li>               
            </div>
            <div>
                문의 : ddoogaenang.naver.com            
            </div>
            <div>
                Copyrightⓒ 2023 ddooganang all rights reserved           
            </div>
        </div>

        <div class="footer3">
      

        </div>
    

        <div class="footer4">
            <div>
                <li><a href=""><img src="https://semiproject.s3.ap-northeast-2.amazonaws.com/%EC%9D%B4%EC%9C%A0%EC%A7%84/twitter.png" width="30px"></a></li>
                <li><a href=""><img src="<%= request.getContextPath() %>/resources/facebook.png" width="25px"></a></li>   
                <li><a href=""><img src="<%= request.getContextPath() %>/resources/instagram.png" width="30px"></a></li>               
            </div>

        </div>


    </div>
    
</body>
</html>