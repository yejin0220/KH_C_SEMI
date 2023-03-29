<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String contextPath = request.getContextPath();
  String alertMsg = (String)session.getAttribute("alertMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산책메이트 게시판 글쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<link href="resources/css/02_mateWrite.css?afterlike" rel="stylesheet">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ae890d646304659e5b68c9a99be204bf"></script>
<style>

</style>
</head>
<body>
	
	 <div class="wrap">
      	 <div class="content1">
          <div class="content_name">
              <img src="<%=contextPath%>/resources/왼쪽강아지.png" style="width: 40px; height: 35px;">
              <h1>같이 걷개</h1>
              <img src="<%=contextPath%>/resources/오른쪽강아지.png" style="width:40px; height:40px;">
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
        <form action="<%=contextPath %>/insert.mate" method="post" ">
          <div class="walk-write">
            <div class="walk-name">
              <div class="search_box">
                <select name="address1" id="" onchange="categoryChange(this)" class="address1" >
                  <option value>광역시/도 선택</option>
                  <option value="general01">강원도</option>
                  <option value="general02">경기도</option>
                  <option value="general03">경상남도</option>
                  <option value="general04">경상북도</option>
                  <option value="general05">광주광역시</option>
                  <option value="general06">대구광역시</option>
                  <option value="general07">대전광역시</option>
                  <option value="general08">부산광역시</option>
                  <option value="general09">서울특별시</option>
                  <option value="general10">울산광역시</option>
                  <option value="general11">인천광역시</option>
                  <option value="general12">전라남도</option>
                  <option value="general13">전라북도</option>
                  <option value="general14">제주도</option>
                  <option value="general15">충청남도</option>
                  <option value="general16">충청북도</option>
                </select>
            </div>
            <div class="search_box">
                <select name="address2" id="state" class="address2">
                    <option>군/구 선택</option>
                </select>
            </div>
              <input type="text" class="title" name="title" placeholder="게시글 제목을 입력하세요" required>
            </div>
            
            <hr>

            <img src="<%=contextPath %>/resources/메이트소개글쓰기.png" height="65">
            <div class="write-content">
              <textarea class="walk" style="resize:none;" name="content"></textarea>
            </div>

            <img src="<%=contextPath %>/resources/메이트 위치 정하기.png" height="68">
            <div id="map">
				
            </div>

            <img src="<%=contextPath %>/resources/사진첨부하기.png" height="70">
            <div id="walk-picture">
              <div class="prev btn-pic"></div>
              <div class="items">
                  <div class="item active">
                      <div class="picture"></div>
                      <div class="picture"></div>
                      <div class="picture"></div>
                  </div>
                  <div class="item">
                      <div class="picture"></div>
                      <div class="picture"></div>
                      <div class="picture"></div>
                  </div>
                  <div class="item">THREE</div>
                  <div class="item">FOUR</div>
                  <div class="item">FIVE</div>
              </div>
              <div class="stepper">
                  <div class="step active-step"></div>
                  <div class="step"></div>
                  <div class="step"></div>
                  <div class="step"></div>
                  <div class="step"></div>
              </div>
              <div class="next btn-pic"></div>
          </div>
                <input type="file" id="file" name="file" required">
          		<label for="file" style="font-size: 13px;">파일선택</label>
            <br>
            <div class="block" style="height: 10px;"></div>
            
          </div>
          <div class="btn-div">
            <button type="submit" class="btn-upload" >등록하기</button>
            <button type="reset" class="btn-reset">취소하기</button>
          </div>
        </form>
      </div>
      

      <div class="footer"></div>



    </div>
    
    <script>
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
	            
	
	        if (e.value == "general01") {
	            add = gangwon;
	        } else if (e.value == "general02") {
	            add = gyeonggi;
	        } else if (e.value == "general03") {
	            add = gyeongsangnam;
	        } else if (e.value == "general04") {
	            add = gyeongsangbuk;
	        } else if (e.value == "general05") {
	            add = gwangju;
	        } else if (e.value == "general06") {
	            add = daegu;
	        } else if (e.value == "general07") {
	            add = daejeon;
	        } else if (e.value == "general08") {
	            add = busan;
	        } else if (e.value == "general09") {
	            add = seoul;
	        } else if (e.value == "general10") {
	            add = ulsan;
	        } else if (e.value == "general11") {
	            add = incheon;
	        } else if (e.value == "general12") {
	            add = jeonnam;
	        } else if (e.value == "general13") {
	            add = jeonbuk;
	        } else if (e.value == "general14") {
	            add = jeju;
	        } else if (e.value == "general15") {
	            add = chungnam;
	        } else if (e.value == "general16") {
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
     <script>
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = { 
                center: new kakao.maps.LatLng(37.566535874777784, 126.97860140554657), // 지도의 중심좌표
                level: 8 // 지도의 확대 레벨
            };
        
        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        
        // 지도를 클릭한 위치에 표출할 마커입니다
        var marker = new kakao.maps.Marker({ 
            // 지도 중심좌표에 마커를 생성합니다 
            position: map.getCenter() 
        }); 
        // 지도에 마커를 표시합니다
        marker.setMap(map);
        
        // 지도에 클릭 이벤트를 등록합니다
        // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
        kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
            
            // 클릭한 위도, 경도 정보를 가져옵니다 
            var latlng = mouseEvent.latLng; 
            
            // 마커 위치를 클릭한 위치로 옮깁니다
            marker.setPosition(latlng);
            
            $("#latitude").val(latlng.getLat());
            $("#longitude").val(latlng.getLng());
    	});
    </script>
    <script>
    	
    		
      		function loadImg(inputFile, num){
      			//inputFile : 현재 변화가 생긴 input type="file"요소
      			//num : 몇 번재 input 요소인지 확인 후 해당 영역에 미리보기 하기 위한 변수
      			
      			console.log(inputFile.files.length);
	      		/*
	      			파일 선택시 length = 1, 파일 선택 취소시 배열안의 내용이 비어있게 됨
	      			length 값을 가지고 파일의 존재 유뮤를 알 수가 있다.
	      			
	      			files속성은 업로드 된 파일의 정보들을 "배열" 형식으로 여러개 묶어서 반환, length그 배열의 크기를 의미
	      		*/
      			
	      		if(inputFile.files.length != 0){
	      			//선택된 파일이 존재할 경우에 선택된 파일들을 읽어들여서 그 영역에 맞는 곳에 미리 보기를 추가
	      			
	      			//파일을 읽어들일 FileReader객체 생성
	      			let reader = new FileReader();
	      			
	      			//파일을 읽어들이는 메소드 -> 어느 파일을 읽을지 매개변수에 제시해줘야 함
	      			//0번째 인덱스에 담긴 파일 정보를 제시
	      			//-> 해당 파일을 읽어들이는 순간 해당 파일만의 고유한 url이 부여됨.
	      			//-> 해당 url을 src 속성값으로 제시

	      			reader.readAsDataURL(inputFile.files[0]);
	      			
	      			//파일 읽기가 완료되었을때 실행할 함수 정의
	      			reader.onload = function(e){ //e.target.result에 고유한 url부여됨.
	      				
	      				//각 영역에 맞워서 이미지 미리보기 제시
	      				let url = e.target.result;
	      				
	      				switch(num){
	      				case 1 : $(".picture").attr("src", url); break;
	      				case 2 : $(".picture").attr("src", url); break;
	      				case 3 : $(",picture").attr("src", url); break;
	      				}
	      				
	      			}
	      		}/* else{
	      			//선택된 파일이 없을 경우,미리 보기도 함께 사라지게끔 작업
		      			switch(num){
	      				case 1 : $("#contentImg1").removeAttr("src"); break;
	      				case 2 : $("#contentImg2").removeAttr("src"); break;
	      				case 3 : $("#contentImg3").removeAttr("src"); break;
	      				} */
	      		}
      			
      		}
      	
    
    	
    	
    </script>
  
</body>
</html>