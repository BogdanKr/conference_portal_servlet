<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/speaker/partspeaker/headspeaker.jsp" %>


<div class="mb-3"><fmt:message key="presentation_editor"/> ${requestScope.conference.date} / ${requestScope.conference.subject}</div>
<form action="${pageContext.request.contextPath}
/conference/speaker/addpresentation" method="post">
    <label ><fmt:message key="speaker"/>
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
    <label><fmt:message key="theme"/>
        <input type="text" name="theme" value="${requestScope.presentation.theme}">
    </label>

    <input type="hidden" name="presentationEditId" value="${requestScope.presentation.id}">
    <input type="hidden" name="presentationConfId" value="${requestScope.conference.id}">
    <button type="submit"><fmt:message key="save"/></button>
</form>


<%@ include file="/WEB-INF/speaker/partspeaker/tailspeaker.jsp" %>
