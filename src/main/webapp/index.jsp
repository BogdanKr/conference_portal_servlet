<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/partsguest/head.jsp" %>


<h5> Welcome Guest
</h5>
This is a conference portal<br>
Це портал конференцій!
<br>
Login or Registration please
<div class="mt-1">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}
    /conference/login" role="button">Login</a>
    <br>
    <a class="btn btn-primary mt-1" href="${pageContext.request.contextPath}
    /conference/registration" role="button">Registration</a>
    <br>
</div>
<br>


<%@ include file="WEB-INF/partsguest/tail.jsp" %>
