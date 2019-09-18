<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h5> <fmt:message key="admin_edit_form"/></h5>

<form action="${pageContext.request.contextPath}
    /conference/admin/edit?id=${user.id}" method="post">

    <label><fmt:message key="first_name"/>
        <input type="text" name="firstName" value="${requestScope.user.firstName}">
    </label>
    <label><fmt:message key="email"/>
        <input type="email" name="email" value="${requestScope.user.email}">
    </label>
    <label><fmt:message key="password"/>
        <input type="password" name="password" value="">
    </label>
    <div class="custom-control custom-switch">
        <input type="checkbox" class="custom-control-input" id="customSwitch1"
        <c:if test="${requestScope.user.active}"> checked </c:if> name="active">
        <label class="custom-control-label" for="customSwitch1"><fmt:message key="active"/></label>
    </div>
    <c:forEach items="${requestScope.roles}" var="role">
        <label><input type="radio" name="role" value="${role}"
        <c:if test="${requestScope.user.role eq role}"> checked </c:if>>${role}</label>
    </c:forEach>
    <input type="hidden" name="userId" value="${requestScope.user.id}">
    <div>
        <button class="btn btn-primary" name="submit" type="submit">
            <fmt:message key="save"/>
        </button>
    </div>
</form>

<%@ include file="/WEB-INF/admin/partadmin/deletemodal.jsp" %>

<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
