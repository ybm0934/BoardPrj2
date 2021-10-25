<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/board/css/boardWrite.css">
</head>
<body>
<div id="wrap">
	<div id="titleDiv">
		<h1>게시판 글쓰기</h1>
	</div>
	<form name="boardWrite" method="post" action="boardWrite.do">
		<table id="writeTable">
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="name" class="box" id="name" placeholder="이름을 입력하세요." required="required">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="pwd" class="box" id="pwd" placeholder="비밀번호를 입력하세요." required="required">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" class="box" id="title" placeholder="제목을 입력하세요." required="required">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" id="content" required="required"></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="reset" id="reset" class="btn" value="다시 작성"> 
					<input type="submit" id="regit" class="btn" value="글쓰기">
				</th>
			</tr>
		</table>
	</form>
</div>
</body>
</html>