<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>항해 일지</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/paintDiary/paintDiary.css'/>">
		<!-- head -->
		<c:import url="/WEB-INF/views/layout/head.jsp"/>
		<script src="<c:url value='/js/paintDiary/paintDiary.js'/>"></script>
	</head>
	<body>
		<div id="wrap">
			<!-- background & header -->
			<c:import url="/WEB-INF/views/layout/header.jsp"/>
			<!-- parentsNav -->
			<c:import url="/WEB-INF/views/layout/parentsNav.jsp"/>
			<div id="paintingWall">
				<div id="paintingWallInfo">
					<h1>항해 일지</h1>
					<p>지금까지 아이와 함께한 모험의 기록</p>
				</div>
				<div id="paintingBox">
					<div class="painting" id="painting1"></div>
					<div class="painting" id="painting2"></div>
					<div class="painting" id="painting3"></div>
					<div class="painting" id="painting4"></div>
					<div class="painting" id="painting5"></div>
					<div class="painting" id="painting6"></div>
				</div>
			</div>
			<!-- bottom -->
			<c:import url="/WEB-INF/views/layout/bottom.jsp"/>
		</div>
	</body>
</html>