<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>


<h5> User list</h5>
<a href="${pageContext.request.contextPath}
/conference/registration">Add user</a>
<table>
    <thead>
    <tr>
        <th>First Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Active</th>
        <th>Role</th>
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
            /conference/admin/edit?id=${user.id}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/index.jsp">Index Page</a>


<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
