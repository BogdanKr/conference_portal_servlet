<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/partsguest/head.jsp" %>


<h5> <fmt:message key="welcome_info" /></h5>

<br>
<fmt:message key="please_login" />
<div class="mt-1">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}
    /conference/login" role="button"><fmt:message key="login" /></a>
    <br>
    <a class="btn btn-primary mt-1" href="${pageContext.request.contextPath}
    /conference/registration" role="button"><fmt:message key="registration" /></a>
    <br>
</div>
<br>


<%@ include file="/WEB-INF/partsguest/tail.jsp" %>
