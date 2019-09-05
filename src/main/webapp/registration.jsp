<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="parts/head.jsp" %>


<c:if test="${requestScope.error eq true}">
    <div class="alert alert-danger" align="center">
        <strong>Invalid email or password</strong>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/conference/registration" method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email </label>
        <div class="col-sm-4">
            <input type="email" name="email" class="form-control" placeholder="Email" required
                   autofocus/>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password </label>
        <div class="col-sm-4 ">
            <input type="password" name="password" class="form-control" placeholder="Password"
                   required/>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">
        Registration
    </button>
</form>

<div class="mt-1">
    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
</div>

<%@ include file="parts/tail.jsp" %>