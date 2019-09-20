<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>

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
            <td><a class="btn btn-primary btn-sm"
                   href="${pageContext.request.contextPath}
                        /conference/admin/editpresentation?presentationEditId=${presentation.id}"><fmt:message key="edit"/></a>
            </td>
            <td><a style="color: #781a20"
                   href="${pageContext.request.contextPath}
                           /conference/admin/delete_presentation?presentationId=${presentation.id}"
                   role="button"><fmt:message key="delete"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
