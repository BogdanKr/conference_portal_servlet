<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>


<table>
    <thead>
    <tr>
        <th><fmt:message key="date"/></th>
        <th><fmt:message key="subject"/></th>
        <th><fmt:message key="presentation"/></th>
        <th><fmt:message key="total"/></th>
        <th><fmt:message key="my_registrations"/></th>
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
                        /conference/admin/conference_registration?confId=${conference.id}" style="color: #b10821">
                <c:if test="${conference.currentUserRegistered}">
                    <i class="fas fa-registered"><fmt:message key="registered"/></i>
                </c:if>
                <c:if test="${!conference.currentUserRegistered}">
                    <i class="far fa-registered"> </i>
                </c:if>
            </a></td>
            <td>
                <%@ include file="/WEB-INF/admin/partadmin/addconfmodal.jsp" %>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
