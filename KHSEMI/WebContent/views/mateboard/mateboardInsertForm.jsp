<%@ page import="com.kh.member.model.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String contextPath = request.getContextPath();
  String alertMsg = (String)session.getAttribute("alertMsg");
  Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산책메이트 게시판 글쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<!-- <link href="resources/css/02_mateWrite.css?after" rel="stylesheet"> -->
<style>
.wrap{
    /* position: relative; */
    width: 100%;
    height: 100%;
  }
  .content_name{
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .content2{
    line-height: 2;
    text-align: center;
  }
  .walk-write{
    /* position: absolute; */
    margin: 0 auto;
    border: 1px solid black;
    width: 70%;
  
    left: 13.5%;
  }
  .walk-name{
    border: 1px solid red;
    width: 95%;
    display: inline-flex;
    margin : 8px 0px 0px 12px;
  }
  .content3{
    display: flex;
    justify-content: space-around;
}
.location{
    display: flex;
}
.address1, .address2{
   height: 1.5rem;
   border-radius: 7px;
   border: 1px solid lightblue;
}
  .write-content, #map{
    border: 3px solid rgb(106, 171, 240);
    border-radius: 15px;
    background-color:  rgb(230, 242, 255);
    width: 95%;
    height: 20rem;
    margin: 0 auto;
  }
  
  #container{
    border: 3px solid rgb(106, 171, 240);
    border-radius: 15px;
    background-color:  rgb(230, 242, 255);
    width: 95%;
    height: 28rem;
    margin: 0 auto;
  }

 .write-info{
 	margin : 15px 0px 10px 10px;
 }

  textarea{
    border-radius: 15px;
    margin-left: 10px;
    padding : 20px 20px;
    
  }

  img{
    margin-top: 20px;
  }
  
 .btn-div{
  display: flex;
  justify-content: center;
  margin-top: 30px;
 }

 .btn-upload, .btn-reset {
	width: 100px;
	height: 40px;
	border-radius: 10px;
	font-size: medium;
	font-weight: 900;
	background-color: white;
}

.btn-upload {
	border: 2px solid rgb(106, 171, 240);
	color: rgb(106, 171, 240);
	margin-right: 50px;
}

.btn-reset {
	border: 2px solid gray;
}



 /*사진보여주기 style*/
 .items {
  /* border: 1px solid red; */
  margin: 0 auto;
  width: 85%;
  height: 450px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.item {
  height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
  display: none;
}

.active {
  display: flex;
  height: 400px;
  width: 1600px;
  color: white;
  position: relative;
  border: 1px solid blue;
}

.btn-pic,
.prev:active,
.prev:focus,
.next:active,
.next:focus{
  position: absolute;
  top: 45%;
  transform: translateY(-50%);
  border: none;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  border: none;
  background-color: white;
  box-shadow: 0px 0px 3px rgb(209, 202, 202);
  outline: none;
  cursor: pointer;
}

.next {
  right: 150px;
  background-image: url(resources/KakaoTalk_20230320_141959451.png);
  background-size: 20px;
  background-repeat: no-repeat;
  background-position: 50% 50%;
  
}
.prev{
  left: 150px;
  background-image: url(resources/KakaoTalk_20230320_141959518.png);
  background-size: 20px;
  background-repeat: no-repeat;
  background-position: 50% 50%;
}
.stepper {
  margin-top: 15px;
  display: flex;
  justify-content: center;
}

.step {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: rgb(218, 214, 214);
  margin: 0 6px;
}

.active-step {
  background-color: darkgray;
}

.active>div {
  width: 1300px;
  height: 100%;
  margin: 15px
}

.picture{
	display: flex;
	justify-content: space-evenly;
	flex-wrap:nowrap;
	border: 1px solid black;
}

.picture img{
	border: 1px solid red;
	border-radius: 30px;
	width: 250px;
	height:300px;

}

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
       <%--  <form action="<%=contextPath %>/insert.mate" method="post" enctype="multipart/form-data"> --%>
        	
			<input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">	
          <div class="walk-write">
            <div class="walk-name">
              <div class="search_box">
                <select name="address1" id="" onchange="categoryChange(this)" class="address1" >
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
                <select name="address2" id="state" class="address2">
                    <option>군/구 선택</option>
                </select>
            </div>
              <input type="text" class="title" name="title" placeholder="게시글 제목을 입력하세요" required>
            </div>
            
            <hr>
            
			

            <img src="<%=contextPath %>/resources/메이트소개글쓰기.png" height="65">
            <div class="write-content">
             <div class="write-info"><span> 한 줄 소개글 : </span> <input type="text" placeholder="산책메이트를 위한 한 줄 소개글을 입력해주세요" size="140"></div>
             <textarea class="walk content" cols="168" rows="15" style="resize:none;" name="content"></textarea>
            </div>
           
            <img src="<%=contextPath %>/resources/메이트 위치 정하기.png" height="68">
            <div id="map"></div>
            
            <input type="text" hidden id="latitude" name="latitude">
            <input type="text" hidden id="longitude" name="longitude">

            <img src="<%=contextPath %>/resources/사진첨부하기.png" height="70">
           	
            <div id="container">
              <div class="prev btn-pic"></div>
                <div class="items">
                  <div class="item active">
                    <div class="picture">
                    	
               			
                    </div>
                  </div>
                </div>
               <div class="next btn-pic"></div>
            </div>
            <input type="file" id="file" accept="image/*" onchange="loadImg(this);" name="file" multiple/>
            <br>
            <div class="block" style="height: 10px;"></div>
            
          </div>
          <div class="btn-div">
            <button type="button" class="btn-upload" onclick="uploadList()">등록하기</button>
            <button type="reset" class="btn-reset"><a href="<%=contextPath%>/list.mate?currentPage=1" style="text-decoration: none; color:gray;">목록가기</a></button>
          </div>
       
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
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c4595a346e879941f9b54bcb0a86f0"></script>
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
	
	  function loadImg(inputFile) {
      	// inputFile : 현재 변화가 생긴 input type="file"요소
      	//console.log(inputFile.files.length);
      	
		let fileLength = inputFile.files.length;
	
      	if(inputFile.files.length != 0){
      		// 선택된 파일이 존재할 경우에 선택된 파일들을 읽어들여서 미리보기 생성
		
      		for(let i=0; i<inputFile.files.length; i++){
          		let reader = new FileReader();
          		
      			reader.readAsDataURL(inputFile.files[i]);
      			
      			reader.onload = function(e){
      				let url = e.target.result;
      				$("<img id='contentImg"+i+"' width='150' height='120' name='contentImg"+i+"'>").appendTo(".picture");
      				$("#contentImg"+i).attr("src", url);
      				
      				
      			}
      		}
      		
      	}else{
      		$(".picture").empty();
      	
      	
      	}
	  }
      
	</script>
	<script>
		function uploadList(){
			let form = new FormData();
			let address1 = $(".address1").val();
			let address2 = $(".address2").val();
			let title = $(".title").val();
			let content = $(".content").val();
			let latitude = $("#latitude").val();
			let longitude = $("#longitude").val();
			
			$.each($("#file")[0].files, function(index, item){
				form.append("file"+index, item);
			})
			form.append("title",title);
			form.append("content", content);
			form.append("address1", address1);
			form.append("address2", address2);
			form.append("latitude", latitude);
			form.append("longitude", longitude);
			form.append("fileLength", $("#file")[0].files.length);
				
			$.ajax({
				url : "<%=contextPath%>/insert.mate",
				data : form,
				type : "post",
				processData : false,
				contentType : false,
				success : function(data){
					console.log("성공");
					location.href="<%=contextPath%>/list.mate?currentPage=1"
				}
				
			})	
			
		}
	</script>
  
</body>
</html>