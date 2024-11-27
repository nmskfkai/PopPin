<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>팝업스토어 지도</title>
    <link rel="stylesheet" href="/resources/css/list.css">
</head>
<body>
	<%@ include file="../includes/header.jsp"%>
	<div class=mapContainer>
    <div id="map"></div> 
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a11eb426bd8d22b527b68106a60722a&libraries=services,clusterer,drawing"></script>
	
	<script type="text/javascript">
        
		// 서버에서 전달된 locations 리스트를 JavaScript 배열로 변환
	    var locations = ${locationsJson};  // JSTL을 사용하여 Java 객체를 JSON 형식으로 변환
		console.log(locations);
	    function initMap() {
	        // 지도 옵션 설정
	        var container = document.getElementById('map'); // 지도를 표시할 DOM 요소
	        var options = {
	            center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울시청을 기본 중심으로 설정
	            level: 3 // 지도 확대 수준
	        };
	        
	        // 지도 생성
	        var map = new kakao.maps.Map(container, options);
	
	        // 위치 배열을 순회하면서 마커를 추가
	        for (var i = 0; i < locations.length; i++) {
	            var location = locations[i];
	            var lat = location.latitude;
	            var lon = location.longitude;
	
	            // 마커를 표시할 위치 생성
	            var markerPosition  = new kakao.maps.LatLng(lat, lon);
	
	            // 마커 객체 생성
	            var marker = new kakao.maps.Marker({
	                position: markerPosition
	            });
	
	            // 마커 지도에 표시
	            marker.setMap(map);
	        }
	    }
	
	    // 지도 초기화
	    window.onload = initMap;
	</script>
</body>
</html>
