<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="parts/head.jsp" %>

<c:if test="${requestScope.error eq true}">
    <div class="alert alert-danger" align="center">
        <strong>${requestScope.message}</strong>
    </div>
</c:if>


<h5> Welcome <c:if test="${sessionScope.userEmail ne null}">${sessionScope.userEmail}</c:if>
    <c:if test="${sessionScope.userEmail eq null}">Guest</c:if>
</h5>
This is a conference portal<br>
Це портал конференцій!
<br>
<c:if test="${sessionScope.userEmail eq null}">
    Login or Registration please
    <div class="mt-1">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/conference/login" role="button">Login</a>
        <br>
        <a class="btn btn-primary mt-1" href="${pageContext.request.contextPath}/conference/registration" role="button">Registration</a>
        <br>
        <a class="btn btn-primary mt-1" href="${pageContext.request.contextPath}/welcome.jsp" role="button">Welcome
            page</a>
    </div>
</c:if>
<br>
<a href="${pageContext.request.contextPath}/conference/exception">Exception</a>


<%@ include file="parts/tail.jsp" %>
