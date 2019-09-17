<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/user/partuser/headuser.jsp" %>



    <h5> Welcome  ${sessionScope.user.firstName} !

    </h5>
    This is a conference portal
    Це портал конференцій!
    <br>

<%@ include file="/WEB-INF/user/partuser/mainuser.jsp" %>


<%@ include file="/WEB-INF/user/partuser/tailuser.jsp" %>
