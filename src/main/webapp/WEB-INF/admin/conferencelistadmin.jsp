<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>


<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Subject</th>
        <th>Presentations</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.conferenceList}" var="conference">
        <tr>
            <td>${conference.date}</td>
            <td class="lm-2">${conference.subject}</td>
            <td class="lm-2">${conference.presentations.size()}</td>
            <td>
                <%@ include file="/WEB-INF/admin/partadmin/addconfmodal.jsp" %>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
