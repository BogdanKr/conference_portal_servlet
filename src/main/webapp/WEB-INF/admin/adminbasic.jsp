<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>


    <h5> <fmt:message key="greeting_admin"/> ${sessionScope.user.firstName} !</h5>
<fmt:message key="greeting_admin2" />

<%@ include file="/WEB-INF/admin/partadmin/mainadmin.jsp" %>



<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
