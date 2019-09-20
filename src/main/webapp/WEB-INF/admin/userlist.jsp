<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>


<h5><fmt:message key="user_list"/></h5>
<a href="${pageContext.request.contextPath}
/conference/admin/adduser"><fmt:message key="add_new_user"/></a>
<table>
    <thead>
    <tr>
        <th><fmt:message key="first_name"/></th>
        <th><fmt:message key="email"/></th>
        <th><fmt:message key="password"/></th>
        <th><fmt:message key="active"/></th>
        <th><fmt:message key="role"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.userList}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td><c:if test="${user.active}">Active</c:if></td>
            <td><a href="${pageContext.request.contextPath}
            /conference/admin/edit?id=${user.id}"><fmt:message key="edit"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
