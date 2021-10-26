<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/board/css/passGate.css">
</head>
<body>
<div id="wrap">
	<form name="passGate" method="post" action="passGate.do">
	<input type="hidden" name="no" value="${param.no }">
	<input type="hidden" name="process" value="${param.process }">
	<fieldset>
		<legend>비밀번호 확인</legend>
		<table id="passTable">
			<tr>
				<th colspan="2">
					<input type="password" name="pwd" id="pwd" placeholder="비밀번호를 입력하세요." required="required" autofocus="autofocus">
				</th>
			</tr>
			<tr>
				<th>
					<input type="button" id="cancel" class="btn" value="취소" onclick="location.href='boardDetail.do?no=${param.no }'">
					<input type="submit" id="confirm" class="btn" value="확인">
				</th>
			</tr>
		</table>
	</fieldset>
	</form>
</div>
</body>
</html>