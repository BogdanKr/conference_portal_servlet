<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/user/partuser/headuser.jsp" %>


<h5>   ${sessionScope.user.firstName} !</h5>

<fmt:message key="welcome_info"/>
<br>

<%@ include file="/WEB-INF/user/partuser/mainuser.jsp" %>

<%@ include file="/WEB-INF/user/partuser/tailuser.jsp" %>
