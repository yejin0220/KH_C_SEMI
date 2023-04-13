<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>또오개냥 메인페이지</title>
<style>
    .dog_land {
        background-image:url('resources/dog_run.gif');
        background-position:center;
        background-repeat:repeat-x; /* 배경이미지 가로로 반복 */
        height: 400px;
        width: 100%;
    }
    .main1, .main3{
        width: 100%;
        text-align: center;
        padding-top: 40px;
    }
    .main1-text, .main3-text{
        font-size: 45px;
        font-family: 'yg-jalnan', verdana, tahoma;
        color: #F7E3A5;
        text-shadow: -2px 0 #4758A8, 0 2px #4758A8, 2px 0 #4758A8, 0 -2px #4758A8;
    }
    .video {
        width: 100%;
        max-width: 900px;
        margin: 30px auto;
        border-radius: 30px;
        background-color: #f0f0f0;
        padding: 40px;
        box-sizing: border-box;
        box-shadow: 0px 8px 33px #999;
    }
    .main2{
        background-color: #E6F2FF;
        height: 600px;
    }
    .video-container {
        position: relative;
        width: 100%;
        height: auto;
        padding-top: 50%;
    }
    iframe {
        z-index: 1;
        top: 0;
        left: 0;
        position: absolute;
        width: 100%;
        height: 100%;
    }
    .main1_img{
        width:100%;
        background-image:url("resources/강아지들.png");
        height: 500px;
        background-position: center;
        background-size: cover;
        background-repeat: no-repeat;
    }
    .chat_icon{
        width: 50px;
        height: 50px;
        float: right;
        position: sticky;
        bottom: 30px;
        margin-right: 20px;
    }
</style>
</head>
<body>
<%@ include file="views/common/menubar.jsp" %>

	<div class="main-content">
        <div class="main1">
            <div class="main1-text" data-aos="fade-up">
                집사들을 위한 <br>반려동물<br> 커뮤니케이션 사이트
            </div>    
            <div class="main1_img" data-aos="fade-up"></div>
        </div>
        <div class="main2">
            <h3>쓸말 없엉</h1>
            <div class="video" data-aos="zoom-in">
                <div class="video-container">
                    <iframe width="560" height="315" src="https://www.youtube.com/embed/X1Ddj5PXfIo" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>                
                </div>
            </div>
        </div>
        
        <div class="main3">
            <div class="main3-text">
                실시간 메이트들의<br>활동을<br>확인해보세요.
            </div>
            <div class="dog_land"></div>
        </div>
        
        <div class="chat_icon">
            <a href="#"><img src="<%= request.getContextPath() %>/resources/chat_icon.png" width="50px"></a>
        </div> 


    </div>



    <script>
        
        AOS.init();

        setInterval(function(){
            $('.dog_land').css({ 'background-position' : '-=7' });
        }, 20);

    </script>


<%@ include file="views/common/footer.jsp" %>
</body>
</html>