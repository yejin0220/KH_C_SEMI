<%@ page import="com.kh.mateboard.model.vo.Board, com.kh.common.model.Attachment, com.kh.member.model.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");	
	Member loginUser =  (Member)session.getAttribute("loginUser");
	String contextPath = (String)request.getContextPath();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link href="resources/css/03_mateDetail.css?afterlike" rel="stylesheet">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>

<body>


	<div class="wrap">
        <div class="content1">
            <div class="content_name">
            <img src="resources/왼쪽강아지.png" style="width: 40px; height: 35px;">
            <h1>&nbsp;같이 걷개&nbsp;</h1>
            <img src="resources/오른쪽강아지.png" style="width:40px; height:40px;">
            </div>
        </div>
        
            
        <hr id="line">
        <br>
        <div class="content2">
            <p>산책메이트와 함께 특별한 시간을 만들어보세요 <br> 
            반려인도 반려동물도 내가 원하는 동네의 다양한 친구들을 만날 수 있어요</p>
        </div>
        
        <br><br>

        <div class="walk-content3">
            
            
	            <div class="walk-write">
	            	
	                <div class="walk-name1">
	                  <span class="address">[<%=b.getAddress() %>]</span>
	                  <span class="title"><%=b.getBoardTitle() %></span>
	                </div>
	                <hr>
	                <div class="walk-name2">
	                  <span class="writer"><%=b.getBoardWriter() %></span>
	                  <span class="createDate"><%=b.getCreateDate() %></span>
	                </div>
	                
	                <hr>
	    
	                <img src="resources/소개글보기-.png" height="60">
	                <div class="write-content">
	    				<textarea  cols="166" rows="13" style="resize:none;" name="content" class="content" readonly="readonly"><%=b.getBoardContent() %></textarea>
	                </div>
	    
	                <img src="resources/메이트만날장소-.png" height="63">
	                <div id="map">
	                </div>
	    
	                <img src="resources/사진구경하기-.png" height="60">
	                
	                <div id="walk-picture">
	                    <div id="container">
	                        <div class="prev btn-pic"></div>
	                          <div class="items">
	                            <div class="item active">
	                              <div class="picture">
	                              	<%if(at == null){ %>
	                              		<p>아직 활동사진이 등록되지 않았습니당</p>
	                              	<%}else { %>
	                              		
	                              		 <img src="<%=contextPath%>/<%=at.getFilePath()+at.getChangeName()%>"> 
	                              	<%} %>
	                              </div>
	                            </div>
	                          </div>
	                          <div class="next btn-pic"></div>
	                      </div>
	                </div>
	                
	                
	            <br>
	             
	        </div>
        
	        <div class="btn-div">
	        	
	        	<%if(loginUser != null&&loginUser.getUserNickname().equals(b.getBoardWriter())) { %>
	        		
	        		<button class="reupload btn btn-info"><a href="<%=contextPath%>/update.mate?bno=<%=b.getBoardNo()%>">수정하기</a></button>
	        		<button class="delete btn btn-outline-warning" onclick="delete">삭제하기</button>
	        	<%}else{ %>
		            <button class="apply btn btn-outline-info">신청하기</button>
	      
	        	<%} %>
	        
	            <button type="reset " class="btn btn-info"><a href="<%=contextPath%>/list.mate?currentPage=1">목록가기</a></button>
	            
	            
	        </div>

    	</div>
    </div>
    
    <script>
    	function delete(){
    		if(!confirm("정말 삭제하시겠습니까?")){
    			return
    		}
    		
    		location.href="<%=contextPath%>/delete.mate?bno=<%=b.getBoardNo()%>";
    	}
    
    </script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c4595a346e879941f9b54bcb0a86f0"></script>
	<script>
	        const lat = <%=b.getLatitude()%>;
	        const long = <%=b.getLongitude()%>;
	        
	        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	            mapOption = { 
	                center: new kakao.maps.LatLng(lat, long), // 지도의 중심좌표
	                level: 3 // 지도의 확대 레벨
	            };
	        
	        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	        
	        var marker = new kakao.maps.Marker({ 
	            // 지도 중심좌표에 마커를 생성합니다 
	            position: map.getCenter() 
	        }); 
	        // 지도에 마커를 표시합니다
	        marker.setMap(map);
     </script>
</body>
</html>