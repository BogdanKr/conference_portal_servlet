<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/parts/head.jsp" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>


<h5> User list</h5>

<table>
    <thead>
    <tr>
        <th>Email</th>
        <th>Password</th>
        <th>Active</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.userList}" var="usr">
<%--        <c:out value="${operation}"/><br />--%>
    <tr>
        <td>${usr.email}</td>
        <td>${usr.password}</td>
        <td>${usr.role}</td>
        <td><c:if test="${usr.active}">Active</c:if></td>
<%--        <td><a href="/registration/${usr.id}">Edit</a> </td>--%>
    </tr>
    </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/index.jsp">Index Page</a>


<%@ include file="/parts/tail.jsp" %>
