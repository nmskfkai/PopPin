<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>팝업스토어 관리</title>
    <link rel="stylesheet" href="/resources/css/manage.css"> <!-- 분리된 CSS 파일 import -->
</head>
<body>
<main>

    <!-- 대기 중인 팝업 -->
    <section class="popup-section">
        <h2>대기 중인 팝업스토어</h2>
        <c:forEach items="${popupStores}" var="store">
            <div class="card">
                <div class="card-details">
                    <p><strong>${store.name}</strong></p>
                    <p>위치: ${store.location}</p>
                    <p>등록 날짜: <fmt:formatDate value="${store.startDate}" pattern="yyyy-MM-dd" /></p>
                </div>
                <div class="card-actions">
                    <button onclick="location.href='/mypage/popupstore/detail?storeId=${store.storeId}'">수정</button>
                    <button class="delete" onclick="deletePopup(${store.storeId})">삭제</button>
                </div>
            </div>
        </c:forEach>
    </section>

    <!-- 승인된 팝업 -->
    <section class="popup-section">
        <h2>승인된 팝업스토어</h2>
        <c:forEach items="${apporovedStores}" var="store">
            <div class="card">
                <div class="card-details">
                    <p><strong>${store.name}</strong></p>
                    <p>위치: ${store.location}</p>
                    <p>등록 날짜: <fmt:formatDate value="${store.startDate}" pattern="yyyy-MM-dd" /></p>
                </div>
                <div class="card-actions">
                    <button onclick="location.href='/mypage/popupstore/detail?storeId=${store.storeId}'">수정</button>
                    <button class="delete" onclick="deletePopup(${store.storeId})">삭제</button>
                </div>
            </div>
        </c:forEach>
    </section>

    <!-- 거절된 팝업 -->
    <section class="popup-section">
        <h2>거절된 팝업스토어</h2>
        <c:forEach items="${rejectedStores}" var="store">
            <div class="card">
                <div class="card-details">
                    <p><strong>${store.name}</strong></p>
                    <p>위치: ${store.location}</p>
                    <p>등록 날짜: <fmt:formatDate value="${store.startDate}" pattern="yyyy-MM-dd" /></p>
                </div>
                <div class="card-actions">
                    <button onclick="location.href='/mypage/popupstore/detail?storeId=${store.storeId}'">수정</button>
                    <button class="delete" onclick="deletePopup(${store.storeId})">삭제</button>
                </div>
            </div>
        </c:forEach>
    </section>
</main>

<script>
    function deletePopup(storeId) {
        if (confirm("정말 삭제하시겠습니까?")) {
            location.href = `/mypage/popupstore/delete?storeId=${storeId}`;
        }
    }
</script>
</body>
</html>
