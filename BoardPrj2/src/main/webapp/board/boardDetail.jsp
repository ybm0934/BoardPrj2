<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/board/css/boardDetail.css">
</head>
<body>
<c:set var="vo" value="${vo }" />
<div id="wrap">
	<div id="titleDiv">
		<h1>글 상세보기</h1>
	</div>
	<table id="detailTable">
		<tr>
			<th>제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>
				<fmt:formatDate value="${vo.regdate }" pattern="yyyy.MM.dd. HH:mm:ss" />
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<div id="content">${vo.content }</div>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="button" id="edit" class="btn" value="수정" onclick="location.href='passGateForm.do?no=${vo.no }&process=edit'">
				<input type="button" id="delete" class="btn" value="삭제" onclick="location.href='passGateForm.do?no=${vo.no }&process=delete'">
			</th>
		</tr>
	</table>
</div>
</body>
</html>