<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/parts/head.jsp" %>


<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Subject</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.conferenceList}" var="conference">
        <tr>
            <td>${conference.date}</td>
            <td class="lm-2">${conference.subject}</td>
            <td>
            <c:if test="${sessionScope.role eq 'ADMIN'}">
                <%@ include file="/WEB-INF/parts/addconferencemodal.jsp" %>
<%--            <td><a href="${pageContext.request.contextPath}/conference/admin/conferenceedit?id=${conference.id}">Edit</a> </td>--%>

            </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/parts/tail.jsp" %>
