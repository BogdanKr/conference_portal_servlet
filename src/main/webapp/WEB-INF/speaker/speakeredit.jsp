<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/speaker/partspeaker/headspeaker.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h5> <fmt:message key="user_edit_form"/></h5>


<form action="${pageContext.request.contextPath}/conference/user/edit" method="post">

    <label> <fmt:message key="first_name"/>
        <input type="text" name="firstName"  value="${requestScope.user.firstName}">
    </label>
    <label> <fmt:message key="email"/>
        <input type="email" name="email"  value="${requestScope.user.email}">
    </label>
    <label> <fmt:message key="password"/>
        <input type="password" name="password" value="">
    </label>
    <div class="custom-control custom-switch">
        <input type="checkbox" class="custom-control-input" id="customSwitch1"
        <c:if test="${requestScope.user.active}"> checked </c:if> name="active" disabled>
        <label class="custom-control-label" for="customSwitch1"> <fmt:message key="active"/></label>
    </div>

    <input type="hidden" name="userId" value="${sessionScope.user.id}">
    <div><button type="submit"> <fmt:message key="save"/></button></div>
</form>

<%@ include file="/WEB-INF/speaker/partspeaker/deletemodalspeaker.jsp" %>

<%@ include file="/WEB-INF/speaker/partspeaker/tailspeaker.jsp" %>
