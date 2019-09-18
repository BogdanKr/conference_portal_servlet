<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
          crossorigin="anonymous">
</head>
<body>

<%@ include file="navbarspeaker.jsp" %>

<div class="container mt-2 pl-0">

    <c:if test="${requestScope.error eq true}">
    <div class="alert alert-danger" align="center">
        <strong>${requestScope.message}</strong>
    </div>
    </c:if>
    <c:if test="${requestScope.success eq true}">
    <div class="alert alert-success" align="center">
        <strong>${requestScope.message}</strong>
    </div>
    </c:if>

