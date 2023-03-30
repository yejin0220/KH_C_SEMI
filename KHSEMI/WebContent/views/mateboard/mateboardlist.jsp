<%@ page import="java.util.ArrayList, com.kh.mateboard.model.vo.Board, com.kh.common.model.PageInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   	String contextPath = request.getContextPath();
   	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	//Board b = (Board)request.getAttribute("b");
	
	
 	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage(); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산책메이트 게시판</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<link href="<%=contextPath%>/resources/css/01.css" rel="stylesheet">
<style>
.boardNo, .count, .create-date{
	display: none;
}
.list{
	border:1px solid black;
	flex-wrap:wrap;
	width:1700px;
	display:flex;
	margin:0 auto;
	padding-left:90px;
}
#card{
	width:300px;
	height:400px;
	margin-bottom:50px;
}

</style>
</head>
<body>

	<%-- <%@ include file="../common/menubar.jsp" %> --%>
	
	 <div class="content1">
          <div class="content_name">
              <img src="<%=contextPath%>/resources/왼쪽강아지.png" style="width: 40px; height: 35px;">
              <h1>같이 걷개</h1>
              <img src="<%=contextPath%>/resources/오른쪽강아지.png" style="width:40px; height:40px;">
          </div>
    </div>
       
     <hr><br>
       
     <div class="content2">
          <p>산책메이트와 함께 특별한 시간을 만들어보세요 <br> 
          반려인도 반려동물도 내가 원하는 동네의 다양한 친구들을 만날 수 있어요</p>
     </div>      
     
     <div class="content3">
            <form action="" method="get" class="location">
                <div class="search_box">
                    <select name="search1" id="" onchange="categoryChange(this)" class="search1">
                      <option value>광역시/도 선택</option>
                      <option value="강원도">강원도</option>
                      <option value="경기도">경기도</option>
                      <option value="경상남도">경상남도</option>
                      <option value="경상북도">경상북도</option>
                      <option value="광주광역시">광주광역시</option>
                      <option value="대구광역시">대구광역시</option>
                      <option value="대전광역시">대전광역시</option>
                      <option value="부산광역시">부산광역시</option>
                      <option value="서울특별시">서울특별시</option>
                      <option value="울산광역시">울산광역시</option>
                      <option value="인천광역시">인천광역시</option>
                      <option value="전라남도">전라남도</option>
                      <option value="전라북도">전라북도</option>
                      <option value="제주도">제주도</option>
                      <option value="충청남도">충청남도</option>
                      <option value="충청북도">충청북도</option>
                    </select>
                </div>
                <div class="search_box">
                    <select name="search2" id="state" class="search2">
                        <option>군/구 선택</option>
                    </select>
                </div>
                <input type="text" size="30" placeholder="제목/내용 검색" class="search-text">
                <button type="submit" id="search-btn">검색</button>
            </form>
            <div class="content3-block" style="width:3%;"></div>
           <button type="button" id="mate_write"><a href="<%=contextPath%>/insert.mate">게시판 글쓰기</a></button>
        </div>
		
		<div class="content4"></div>
		
		<div class="conten5">
		<div class="list">
				<%if(list.isEmpty()){ %>
					<p>등록된 게시글이 없습니다..</p>
				<%}else{ %>
					
					<% for(Board b : list){%>
		                <div class="card" id="card">
		                		<span class="boardNo" style="font-size: x-small;"><%=b.getBoardNo() %></span>
			                    <div class="card-body">
			                    <img class="card-img" src="<%=contextPath %>/resources/분홍발자국.png">   
			                    <span class="card-title"><%=b.getBoardWriter() %></span> 
			                    <span class="card-subtitle mb-2 text-muted"><%=b.getBoardTitle() %></span>
			                    <hr>
			                    <div class="card-content">
			                          	<p>	<%=%></p>
			                          	<p> <%=b.getBoardContent() %></p>
			                    </div>
			                    <div class="card-footer">
			                            <img class="card-thumb" src="<%=contextPath %>/resources/빈 추천.png"><span class="thumb-number">추천수</span>
			                            <img class="card-heart" src="<%=contextPath %>/resources/빈하트.png"><span class="heart-number">참여자수</span>
			                    </div>
			                     <span class="count" style="font-size: small;"><%=b.getCount() %></span>
			                     <span class="create-date" style="font-size: small;"><%=b.getCreateDate() %></span>               
		                    </div>
		                </div>
					
					<%} %>
					
				<%} %>
				</div>
			</div>
          <%--   <div class="profile">

                <div class="card">
                    <div class="card-body">
                    <img class="card-img" src="<%=contextPath %>/resources/분홍발자국.png">   
                    <span class="card-title">또오개냥</span> 
                    <span class="card-subtitle mb-2 text-muted">함께 산책할 친구를 구합니다</span>
                    <hr>
                    <div class="card-content">
                            <p> 산책로 지역 : </p>
                            <p> 반려견 정보 : </p>
                            <p> 유의사항 : </p>
                    </div>
                    <div class="card-footer">
                            <img class="card-thumb" src="<%=contextPath %>/resources/빈 추천.png"><span class="thumb-number">추천수</span>
                            <img class="card-heart" src="<%=contextPath %>/resources//빈하트.png"><span class="heart-number">참여자수</span>
                    </div>               
                    </div>
                </div>

                <div class="card" >
                    <div class="card-body">
                    <img class="card-img" src="<%=contextPath %>/resources/분홍발자국.png">   
                    <span class="card-title">또오개냥</span> 
                    <span class="card-subtitle mb-2 text-muted">함께 산책할 친구를 구합니다</span>
                    <hr>
                    <div class="card-content">
                            <p> 산책로 지역 : </p>
                            <p> 반려견 정보 : </p>
                            <p> 유의사항 : </p>
                    </div>
                    <div class="card-footer">
                            <img class="card-thumb" src="<%=contextPath %>/resources/빈 추천.png"><span class="thumb-number">추천수</span>
                            <img class="card-heart" src="<%=contextPath %>/resources/빈하트.png"><span class="heart-number">참여자수</span>
                    </div>               
                    </div>
                </div>

                <div class="card" >
                    <div class="card-body">
                        <img class="card-img" src="<%=contextPath %>/resources/분홍발자국.png">   
                        <span class="card-title">또오개냥</span> 
                        <span class="card-subtitle mb-2 text-muted">함께 산책할 친구를 구합니다</span>
                        <hr>
                        <div class="card-content">
                                <p> 산책로 지역 : </p>
                                <p> 반려견 정보 : </p>
                                <p> 유의사항 : </p>
                        </div>
                        <div class="card-footer">
                                <img class="card-thumb" src="<%=contextPath %>/resources/빈 추천.png"><span class="thumb-number">추천수</span>
                                <img class="card-heart" src="<%=contextPath %>/resources/빈하트.png"><span class="heart-number">참여자수</span>
                        </div>               
                    </div>
                </div>
            </div>
             --%>
             <!-- 페이징바 영역 -->
	         <nav aria-label="Page navigation example">
	            <ul class="pagination">
	            
	            <% if(currentPage != 1) { %>
	              <li class="page-item">
	                <a class="page-link" href="<%=contextPath %>/list.mate?currentPage=<%=currentPage -1 %>" aria-label="Previous">
	                  <span aria-hidden="true">&laquo;</span>
	                </a>
	              </li>
	        	<%} %>
	             
	             <%for(int i = startPage; i<=endPage; i++) { %>
	             
	             	<%if( i!= currentPage) { %>
	             		<li class="page-item">
	             			<a class="page-link" href="<%=contextPath%>/list.mate?currentPage=<%=i%>"><%=i %></a>
	             		</li>
	             	<%}else{ %>
	             		<li class="page-item"><%=i %></li>
	             	<%} %>
		    
	             <%} %>
	             
	             <%if(currentPage != maxPage){ %>
		              <li class="page-item">
		                <a class="page-link" href="#" aria-label="Next">
		                  <span aria-hidden="true">&raquo;</span>
		                </a>
		              </li>
	              <%} %>
	            </ul>
	        </nav>
         
        
        <script>
	        $(function(){
	            let num = 0;
	            $(".card-thumb").click(function(){
	                if(num == 0){
	                    $(this).attr("src", "resources/꽉찬 추천.png");
	                    $(".thumb-number").text("1");
	                    num = 1;
	                }else{
	                    $(this).attr("src","resources/빈 추천.png");
	                    $(".thumb-number").text("추천수");
	                    num =0;
	                }
	            })
	
	            let num1 = 0;
	            $(".card-heart").click(function(){
	                if(num1 == 0){
	                    $(this).attr("src", "resources/꽉찬하트.png");
	                    num1 = 1;
	                }else{
	                    $(this).attr("src","resources/빈하트.png");
	                    num1 = 0;
	                }
	            })
	        })
	
	        function categoryChange(e) {
	            const state = document.getElementById("state");
	
	            const gangwon = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
	            const gyeonggi = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
	            const gyeongsangnam = ["거제시", "김해시", "마산시", "밀양시", "사천시", "양산시", "진주시", "진해시", "창원시", "통영시", "거창군", "고성군", "남해군", "산청군", "의령군", "창녕군", "하동군", "함안군", "함양군", "합천군"];
	            const gyeongsangbuk = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
	            const gwangju = ["광산구", "남구", "동구", "북구", "서구"];
	            const daegu = ["남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군"];
	            const daejeon = ["대덕구", "동구", "서구", "유성구", "중구"];
	            const busan = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
	            const seoul = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
	            const ulsan = ["남구","동구","북구","중구","울주군"];
	            const incheon = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
	            const jeonnam = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
	            const jeonbuk = ["군산시", "김제시", "남원시", "익산시", "전주시", "정읍시", "고창군", "무주군", "부안군", "순창군", "완주군", "임실군", "장수군", "진안군"];
	            const jeju = ["서귀포시","제주시","남제주군","북제주군"];
	            const chungbuk = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
	                
	
	            if (e.value == "강원도") {
	                add = gangwon;
	            } else if (e.value == "경기도") {
	                add = gyeonggi;
	            } else if (e.value == "경상남도") {
	                add = gyeongsangnam;
	            } else if (e.value == "경상북도") {
	                add = gyeongsangbuk;
	            } else if (e.value == "광주광역시") {
	                add = gwangju;
	            } else if (e.value == "대구광역시") {
	                add = daegu;
	            } else if (e.value == "대전광역시") {
	                add = daejeon;
	            } else if (e.value == "부산광역시") {
	                add = busan;
	            } else if (e.value == "서울특별시") {
	                add = seoul;
	            } else if (e.value == "울산광역시") {
	                add = ulsan;
	            } else if (e.value == "인천광역시") {
	                add = incheon;
	            } else if (e.value == "전라남도") {
	                add = jeonnam;
	            } else if (e.value == "전라북도") {
	                add = jeonbuk;
	            } else if (e.value == "제주도") {
	                add = jeju;
	            } else if (e.value == "충청남도") {
	                add = chungnam;
	            } else if (e.value == "충청북도") {
	                add = chungbuk;
	            }
	
	            state.options.length = 1;
	                // 군/구 갯수;
	
	                    for (property in add) {
	                        let opt = document.createElement("option");
	                        opt.value = add[property];
	                        opt.innerHTML = add[property];
	                        state.appendChild(opt);
	                    }
	        }

        
        </script>
        
	
	
	
	
</body>
</html>