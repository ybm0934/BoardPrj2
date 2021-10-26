<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/board/css/boardList.css">
</head>
<body>
<c:set var="list" value="${list }" />
<c:set var="pvo" value="${pvo }" />
<c:set var="category" value="${category }" />
<c:set var="keyword" value="${keyword }" />
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
		<c:set var="curPos" value="${pvo.curPos }" />
		<c:set var="num" value="${pvo.num }" />
		<c:forEach var="i" begin="1" end="${pvo.pageSize }">
		<c:if test="${num >= 1}">
		<c:set var="vo" value="${list[curPos] }" />
		<c:set var="curPos" value="${curPos + 1 }" />
		<c:set var="num" value="${num - 1 }" />
		<tr>
			<td>${vo.no }</td>
			<td>
				<a href="boardDetail.do?no=${vo.no }">${vo.title }</a>
			</td>
			<td>${vo.name }</td>
			<td><fmt:formatDate value="${vo.regdate }" pattern="yyyy.MM.dd. HH:mm:ss" /></td>
		</tr>
		</c:if>
		</c:forEach>
		</c:if>
	</table>
	<div id="btnDiv">
		<input type="button" id="writeBtn" value="글쓰기" onclick="location.href='boardWriteForm.do'">
	</div>
	<div id="pagingDiv">
		<c:if test="${pvo.firstPage > 1 }">
			<a href="boardList.do?currentPage=1&category=${category }&keyword=${keyword }">&lt;&lt;</a>
			<a href="boardList.do?currentPage=${pvo.firstPage - 1 }&category=${category }&keyword=${keyword }">PREV</a>
		</c:if>
		
		<c:forEach var="i" begin="${pvo.firstPage }" end="${pvo.totalPage }">
		<c:if test="${pvo.currentPage == i }">
			<span class="curSpan">${i }</span>
		</c:if>
		<c:if test="${pvo.currentPage != i }">
			<a href="boardList.do?currentPage=${i }&category=${category }&keyword=${keyword }">${i }</a>
		</c:if>
		</c:forEach>
		
		<c:if test="${pvo.lastPage < pvo.totalPage }">
			<a href="boardList.do?currentPage=${pvo.lastPage + 1 }&category=${category }&keyword=${keyword }">NEXT</a>
			<a href="boardList.do?currentPage=${pvo.totalPage }&category=${category }&keyword=${keyword }">&gt;&gt;</a>
		</c:if>
	</div>
	<div id="searchDiv">
	<form name="search" action="boardList.do">
		<select name="category" id="category">
			<option value="title" 
			<c:if test="${category == 'title' }">selected="selected"</c:if>
			>제목</option>
			<option value="name" 
			<c:if test="${category == 'name' }">selected="selected"</c:if>
			>작성자</option>
			<option value="content" 
			<c:if test="${category == 'content' }">selected="selected"</c:if>
			>내용</option>
		</select>
		<input type="text" name="keyword" class="box" value="${keyword }" placeholder="검색어를 입력하세요." spellcheck="false">
		<input type="submit" class="btn" value="검색">
	</form>
	</div>
</div>
</body>
</html>