<%@ page
	import="com.kh.board.mateboard.model.vo.Board, com.kh.board.model.vo.Attachment, com.kh.member.model.vo.Member, java.util.ArrayList, com.kh.board.mateboard.model.vo.Reply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Board b = (Board) request.getAttribute("b");
//Attachment at = (Attachment)request.getAttribute("at"); 
ArrayList<Attachment> atList = (ArrayList) request.getAttribute("atList");
ArrayList<Reply> list = (ArrayList<Reply>) request.getAttribute("list");
Member loginUser = (Member) session.getAttribute("loginUser");
String contextPath = (String) request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link href="resources/css/03_mateDetail.css?afterlike" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<style>

.btn {
	width: 100px;
	height: 40px;
	border-radius: 10px;
	font-size: medium;
	font-weight: 900;
	background-color: white;
}

.reupload {
	border: 2px solid rgb(106, 171, 240);
}

.list, .delete-reply {
	border: 2px solid gray;
}

.apply {
	border: 2px solid #FF8AAE;
	color: #FF8AAE;
}
.moadl-container{
            display: flex;
            justify-content: space-evenly;
            align-items: flex-start;
            padding: 10px;
        }
        #modal.modal-overlay {
            width: 100%;
            height: 100%;
            position: absolute;
            left: 0;
            top: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            background: rgba(255, 255, 255, 0.25);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            backdrop-filter: blur(1.5px);
            -webkit-backdrop-filter: blur(1.5px);
            border-radius: 10px;
            border: 1px solid rgba(255, 255, 255, 0.18);
            display:none;
        }
        #modal .modal-window {
            background:   rgb(251, 246, 240);
            box-shadow: 0 8px 32px 0 rgba(133, 133, 134, 0.37);
            backdrop-filter: blur( 13.5px );
            -webkit-backdrop-filter: blur( 13.5px );
            border-radius: 10px;
            border: 1px solid rgba( 255, 255, 255, 0.18 );
            width: 500px;
            height: 200px;
            position: relative;
            top: -100px;
            padding: 10px;
        }
        .btn-area{
            text-align: center;
            padding-right: 25px;
            margin-top: 25px;
        }
.user-reply{
	display: flex;
}
.delete-reply{
	width:70px;
	height:20px;
	font-size: x-small;
	margin-top:80px;
	
}
</style>
</head>

<body>


	<div class="wrap">
		<div class="content1">
			<div class="content_name">
				<img src="resources/왼쪽강아지.png" style="width: 40px; height: 35px;">
				<h1>&nbsp;같이 걷개&nbsp;</h1>
				<img src="resources/오른쪽강아지.png" style="width: 40px; height: 40px;">
			</div>
		</div>


		<hr id="line">
		<br>
		<div class="content2">
			<p>
				산책메이트와 함께 특별한 시간을 만들어보세요 <br> 반려인도 반려동물도 내가 원하는 동네의 다양한 친구들을 만날
				수 있어요
			</p>
		</div>

		<br> <br>

		<div class="walk-content3">


			<div class="walk-write">

				<div class="walk-name1">
					<span class="address">[<%=b.getAddress()%>]
					</span> <span class="title"><%=b.getBoardTitle()%></span>
					<input type="hidden" name="bno" value="<%= b.getBoardNo()%>">
				</div>
				<hr>
				<div class="walk-name2">
					<span class="writer"><%=b.getBoardWriter()%></span> 
					<span class="createDate"><%=b.getCreateDate()%></span>
				
				</div>

				<hr>

				<img src="resources/소개글보기-.png" height="60">
				<div class="write-content">
					<textarea cols="166" rows="13" style="resize: none;" name="content"
						class="content" readonly="readonly"><%=b.getBoardContent()%></textarea>
				</div>

				<img src="resources/메이트만날장소-.png" height="63">
				<div id="map"></div>

				<img src="resources/사진구경하기-.png" height="60">

				<div id="walk-picture">
					<div id="container">
						<div class="prev btn-pic"></div>
						<div class="items">
							<div class="item active">
								<div class="picture">
									<%
										if (atList == null) {
									%>
									<p>아직 활동사진이 등록되지 않았습니당</p>
									<%
										} else {
									%>
									<%
										for (Attachment a : atList) {
									%>
									<img
										src="<%=contextPath + a.getFilePath() + a.getChangeName()%>">
									<%
										}
									%>
									<%
										}
									%>
								</div>
							</div>
						</div>
						<div class="next btn-pic"></div>
					</div>
				</div>


				<br>
				<div class="reply-area">
					<img src="<%=contextPath%>/resources/댓글.png" height="60">
					<div class="reply">
						<table>
							<thead>
					<%if(list == null){ %>
						<p>아직 댓글이 없개냥!</p>
					
					<%}else{ %>
						<%for(Reply r : list) {%>
							<tr>
							<div class="user-reply">
								<div class="reply-box" vlaue="123">
									<img src="resources/image2/bono.jpg" class="reply-profile">
									<div class="reply-content">
										<p id="user-nick" ><%=r.getUserNickname()%></p>
										<span id="user-reply"><%=r.getReplyContent() %></span>
									</div>
								</div>
								<%if(r.getUserId().equals(loginUser.getUserId())) {%>
								<button class="delete-reply btn" onclick="deletereply();">
									<input type="hidden" name="bno" value="<%=b.getBoardNo()%>">
								<a href="<%=contextPath%>/deletereply?rno=<%=r.getReplyNo()%>">삭제</a></button>
								<%} %>
							</div>
							</tr>
							
						<%} %> 
					<%} %>  
							</thead>
							<tbody>

								<%
									if (loginUser != null) {
								%>
								<tr>
									<div class="reply-write">
										<div class="reply-user">
											<img src="resources/image2/flower4.jpg" class="reply-profile">
											<p id="user-nick" style="font-size: small;"><%=loginUser.getUserNickname()%></p>
										</div>
										<textarea id="replyContent" cols="160" rows="5"
											style="resize: none;"></textarea>
										<button class="reply-btn" onclick="insertReply();">등록하기</button>
									</div>
								</tr>
								<%
									} else {
								%>
								<tr>
									<div class="reply-write">
										<div class="reply-user">
											<img src="resources/image2/flower4.jpg" class="reply-profile">
											<p id="user-nick">사용자닉넴</p>
										</div>
										<textarea class="replytextara" cols="165" rows="3"
											style="resize: none;" readonly>
											로그인 후 이용가능한 서비스입니다.
										</textarea>
									</div>
								</tr>
								<%
									}
								%>

							</tbody>
						</table>
					</div>
				</div>

			</div>

			<div class="btn-div">
				<%if (loginUser != null && loginUser.getUserNickname().equals(b.getBoardWriter())) {%>
					<button class="btn reupload ">
						<a href="<%=contextPath%>/update.mate?bno=<%=b.getBoardNo()%>" style="text-decoration: none; color: rgb(106, 171, 240);">수정하기</a>
					</button>
					<button class="btn deleteboard">삭제하기</button>
				<%} else {%>
					<button class="btn apply" onclick="apply();">신청하기</button>
				<%}%>

				<button type="reset " class="btn list">
					<a href="<%=contextPath%>/list.mate?currentPage=1" style="text-decoration: none; color: gray;">목록가기</a>
				</button>
			</div>
		</div>
	</div>
	
	<!-- 모달창 -->
	<div class="modal-overlay" id="modal">
        <div class="modal-window">
            <div class="modal-header">
                <h3 class="modal-title" style="font-weight: bold;">게시글 삭제</h3>
                <div type="button" class="close">X</div>
            </div>
            <div class="moadl-container">
                <div class="item">
                    <span>게시글을 삭제하시겠습니까?</span>
                    <div class="btn-area">
                        <button class="btn btn-primary btn-sm" onclick="deleteMate()">삭제</button>
                        <button  class="btn btn-primary btn-sm close">취소</button>
                    </div>
                 </div>
            </div>
        </div>
    </div>
	
	
	<script>
		$(function(){
			$(".deleteboard").click(function(){
				$(".modal-overlay").css("display","block");
			})
		})
		function deleteMate(){
			//게시글 삭제하기
			location.href="<%=contextPath%>/deleteMate?bno=<%=b.getBoardNo()%>"; 
		}
		function apply() {
			//산책메이트 신청하기
			location.href="<%=contextPath%>/applyMate?bno=bno=<%=b.getBoardNo()%>";
		}
		function insertReply(){
			$.ajax({
				url:"<%=contextPath%>/replyInsert",
				Type : "post",
				data:{
					content : $("#replyContent").val(),
					bno		 : "<%=b.getBoardNo()%>"
				},
				success : function(result){
					//댓글등록 성공시 result =1 /실패시 0->댓글내용 비워주기
					if(result > 0){
						//댓글등록 성공하면 댓글목록 불러오고 댓글 내용 비워주기
						selectReplyList();
						$("#replyContent").val("");
						console.log("성공");
					}else{
						alert("댓글 작성실패");
					}    				
				},
				error : function(){
					console.log("댓글작성실패");
				}
			})
		}
		function selectReplyList(){
			$.ajax({
				url : "<%=contextPath%>/replyList",
				data : {bno : <%=b.getBoardNo()%>},
				dataType:"json",
				async :false,
				success : function(list){
						let result="";
						
						for(let i of list){
									result += "<tr>"+"<div class='reply-box'>"+
									 "<img src='resources/image2/bono.jpg' class='reply-profile'>"+
									 "<div class='user-nick'>" +"<p id='usernick'>"+ i.userNickName  +"</p>"+
									 "<span id='user-reply'>"+ i.replyContent + "</span>"+
									 "</div>"+"</div>"+"</tr>"
								}
					$(".reply thead").html(result);
					},
				error : function(){
					console.log("안불러옴");
				}
			});
		}

    </script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c4595a346e879941f9b54bcb0a86f0"></script>
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
       <script>
        $(function(){
            $(".close").click(function(){
                $("#modal").css("display","none");
            })
        })
    </script>

</body>
</html>