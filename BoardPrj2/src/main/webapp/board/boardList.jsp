<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/board/css/boardList.css">
</head>
<body>
<c:set var="list" value="${list }" />
<div id="wrap">
	<div id="titleDiv">
		<h1>게시판</h1>
	</div>
	<table id="listTable">
		<colgroup>
			<col width="10%;">
			<col width="*;">
			<col width="10%;">
			<col width="20%;">
		</colgroup>
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>등록일</th>
		</tr>
		<c:if test="${empty list }">
		<tr>
			<td colspan="4" style="text-align: center;">등록된 글이 없습니다.</td>
		</tr>
		</c:if>
		<c:if test="${!empty list }">
		<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.no }</td>
			<td>${vo.title }</td>
			<td>${vo.name }</td>
			<td>${vo.regdate }</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
	<div id="btnDiv">
		<input type="button" id="writeBtn" value="글쓰기" onclick="location.href='boardWriteForm.do'">
	</div>
</div>
</body>
</html>