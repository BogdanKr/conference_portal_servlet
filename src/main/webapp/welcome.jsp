<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/parts/head.jsp" %>



<h5> Welcome <c:if test="${sessionScope.userEmail ne null}">${sessionScope.user.firtsName}</c:if>
    <c:if test="${sessionScope.userEmail eq null}">Guest</c:if>
</h5>

    This is a conference portal
    Це портал конференцій!
    <br>



<%@ include file="WEB-INF/parts/tail.jsp" %>
