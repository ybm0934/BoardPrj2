<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String) request.getAttribute("msg");
	String url = (String) request.getAttribute("url");
%>
<script>
	if('<%=msg %>' != null && '<%=msg %>' != '') {
		alert('<%=msg %>');
	}
	location.href = '<%=url %>';
</script>