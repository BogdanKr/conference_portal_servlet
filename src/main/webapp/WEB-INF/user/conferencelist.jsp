<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/user/partuser/headuser.jsp" %>


<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Subject</th>
        <th>Presentations</th>
        <th>Total registered</th>
        <th>My registration</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.conferenceList}" var="conference">
        <tr>
            <td>${conference.date}</td>
            <td class="lm-2">${conference.subject}</td>
            <td class="lm-2">${conference.presentations.size()}</td>
            <td class="lm-2">${conference.userRegistrations.size()}</td>
            <td class="lm-2"><a href="${pageContext.request.contextPath}
                        /conference/user/conference_registration?confId=${conference.id}" style="color: #b10821">
                <c:if test="${conference.currentUserRegistered}">
                    <i class="fas fa-registered">I'm registered </i>
                </c:if>
                <c:if test="${!conference.currentUserRegistered}">
                    <i class="far fa-registered"> </i>
                </c:if>
            </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/user/partuser/tailuser.jsp" %>
