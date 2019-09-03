<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="parts/head.jsp" %>


<h5> Welcome Guest

</h5>
This is a conference portal
Це портал конференцій!
<br>
Login or Registration please
<div class="mt-1">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/login.jsp" role="button">Login</a>
    <br>
    <a class="btn btn-primary mt-1" href="${pageContext.request.contextPath}/registration.jsp" role="button">Registration</a>
    <br>
    <a class="btn btn-primary mt-1" href="${pageContext.request.contextPath}/welcome.jsp" role="button">Welcome page</a>
</div>
<br>
<a href="${pageContext.request.contextPath}/conference/exception">Exception</a>


<%@ include file="parts/tail.jsp" %>
