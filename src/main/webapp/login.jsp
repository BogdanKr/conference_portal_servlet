<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="WEB-INF/partsguest/head.jsp" %>

<fmt:message key="login_page"/>
<form action="${pageContext.request.contextPath}
/conference/login" method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="emailInput"> <fmt:message key="email"/> </label>
        <div class="col-sm-4">
            <input type="email" name="email" class="form-control" placeholder="Email" required
            autofocus id="emailInput"/>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> <fmt:message key="password"/>  </label>
        <div class="col-sm-4 ">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">
        <fmt:message key="login"/>
    </button>
</form>

<div class="mt-1">
   <a href="${pageContext.request.contextPath}/registration.jsp"><fmt:message key="registration"/></a>
</div>

<%@ include file="WEB-INF/partsguest/tail.jsp" %>