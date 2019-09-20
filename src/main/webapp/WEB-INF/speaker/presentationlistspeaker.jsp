<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/speaker/partspeaker/headspeaker.jsp" %>

<table style="text-align: center">
    <thead>
    <tr>
        <th><fmt:message key="date"/></th>
        <th><fmt:message key="conference_day"/></th>
        <th><fmt:message key="speaker"/></th>
        <th><fmt:message key="theme"/></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.presentationList}" var="presentation">
        <tr>
            <td>${presentation.conference.date}</td>
            <td>${presentation.conference.subject}</td>
            <td>${presentation.author.firstName}</td>
            <td class="lm-2">${presentation.theme}</td>
            <c:if test="${sessionScope.user.id eq presentation.author.id}">
            <td>
                <a href="${pageContext.request.contextPath}
                /conference/speaker/editpresentation?presentationEditId=${presentation.id}"><fmt:message key="edit"/> </a>
            </td>
            <td><a style="color: #781a20"
                   href="${pageContext.request.contextPath}
                           /conference/speaker/delete_presentation?presentationId=${presentation.id}"
                   role="button"><fmt:message key="delete"/></a>
            </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/speaker/partspeaker/tailspeaker.jsp" %>
