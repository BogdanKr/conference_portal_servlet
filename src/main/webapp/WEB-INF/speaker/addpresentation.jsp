<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/speaker/partspeaker/headspeaker.jsp" %>


<div class="mb-3">Presentation edd for ${requestScope.conference.date} / ${requestScope.conference.subject}</div>
<form action="${pageContext.request.contextPath}/conference/speaker/addpresentation" method="post">
    <label >Speaker
        <input type="text" name="firstName" readonly
        <c:choose>
        <c:when test="${requestScope.presentation ne null}">
            value=" ${requestScope.presentation.author.firstName}"
        </c:when>
        <c:when test="${requestScope.presentation eq null}">
               value="${sessionScope.user.firstName}"
        </c:when>
        </c:choose>
        >
    </label>
    <label>Presentation theme
        <input type="text" name="theme" value="${requestScope.presentation.theme}">
    </label>

    <input type="hidden" name="presentationEditId" value="${requestScope.presentation.id}">
    <input type="hidden" name="presentationConfId" value="${requestScope.conference.id}">
    <button type="submit">Save</button>
</form>


<%@ include file="/WEB-INF/speaker/partspeaker/tailspeaker.jsp" %>
