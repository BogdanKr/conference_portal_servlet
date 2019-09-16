<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>

<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Conference</th>
        <th>Speaker</th>
        <th>Presentation theme</th>
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
            <td><a href="${pageContext.request.contextPath}
                /conference/speaker/editpresentation?presentationEditId=${presentation.id}">Edit admin</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
