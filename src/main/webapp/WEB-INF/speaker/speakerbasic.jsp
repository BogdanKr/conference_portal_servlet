<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/speaker/partspeaker/headspeaker.jsp" %>


<h5><fmt:message key="greeting_speaker"/>  ${sessionScope.user.firstName} !

</h5>
<fmt:message key="greeting_speaker2"/>
<br>

<%@ include file="/WEB-INF/speaker/partspeaker/mainspeaker.jsp" %>


<%@ include file="/WEB-INF/speaker/partspeaker/tailspeaker.jsp" %>
