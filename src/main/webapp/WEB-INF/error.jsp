<%@ page language="java" isErrorPage="true"  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/parts/head.jsp" %>


<h2>
    Error Page<br/>
    <i>Error <%= exception %></i>
</h2>
<br>
<a href="${pageContext.request.contextPath}/index.jsp">Index Page</a>


<%@ include file="/parts/tail.jsp" %>
