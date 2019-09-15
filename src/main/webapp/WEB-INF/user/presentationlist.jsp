<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/parts/head.jsp" %>

<c:if test="${requestScope.success eq true}">
    <div class="alert alert-success" align="center">
        <strong>${requestScope.message}</strong>
    </div>
</c:if>

<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Presentation theme</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.presentationList}" var="presentation">
        <tr>
            <td>${presentation.conference.date}</td>
            <td class="lm-2">${presentation.theme}</td>
            <td>
            <c:if test="${sessionScope.user.id eq presentation.author.id}">
                <a href="${pageContext.request.contextPath}
                /conference/speaker/addpresentation?presentationEditId=${presentation.id}">Edit </a>
            </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/parts/tail.jsp" %>