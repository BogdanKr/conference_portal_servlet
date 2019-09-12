<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/parts/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${requestScope.success eq true}">
    <div class="alert alert-success" align="center">
        <strong>${requestScope.message}</strong>
    </div>
</c:if>

<h5> User edit form</h5>


<form action="${pageContext.request.contextPath}/conference/user/edit" method="post">

    <label>First Name
        <input type="text" name="firstName"  value="${requestScope.user.firstName}">
    </label>
    <label>Email
        <input type="email" name="email"  value="${requestScope.user.email}">
    </label>
    <label>Password
        <input type="password" name="password" value="">
    </label>
    <div class="custom-control custom-switch">
        <input type="checkbox" class="custom-control-input" id="customSwitch1"
        <c:if test="${requestScope.user.active}"> checked </c:if> name="active" disabled>
        <label class="custom-control-label" for="customSwitch1">Active</label>
    </div>

    <input type="hidden" name="userId" value="${sessionScope.user.id}">
    <div><button type="submit">Edit</button></div>
</form>

<%@ include file="/WEB-INF/parts/deletemodal.jsp" %>

<%@ include file="/WEB-INF/parts/tail.jsp" %>
