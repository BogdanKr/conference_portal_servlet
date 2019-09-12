<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/parts/head.jsp" %>


<c:if test="${requestScope.error eq true}">
    <div class="alert alert-danger" align="center">
        <strong>${requestScope.message}</strong>
    </div>
</c:if>
<c:if test="${requestScope.success eq true}">
    <div class="alert alert-success" align="center">
        <strong>${requestScope.message}</strong>
    </div>
</c:if>

    <h5> Welcome ADMIN !

    </h5>
    This is a conference portal
    Це портал конференцій!
    <br>

<%@ include file="/WEB-INF/parts/main.jsp" %>



<%@ include file="/WEB-INF/parts/tail.jsp" %>
