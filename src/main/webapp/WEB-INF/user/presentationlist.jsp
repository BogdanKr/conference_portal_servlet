<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/user/partuser/headuser.jsp" %>

<table>
    <thead>
    <tr>
        <th><fmt:message key="date"/> </th>
        <th><fmt:message key="conference_day"/> </th>
        <th><fmt:message key="speaker"/> </th>
        <th><fmt:message key="theme"/> </th>
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
            <td>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/user/partuser/tailuser.jsp" %>
