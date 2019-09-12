<c:if test="${sessionScope.role eq 'ADMIN'}">
    <%@ include file="/WEB-INF/parts/addconferencemodal.jsp" %>
</c:if>

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
            <td>${conference.subject}</td>
            <td><a href="${pageContext.request.contextPath}/conference/admin/conferenceedit?id=${conference.id}">Edit</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>