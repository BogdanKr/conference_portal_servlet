<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/speaker/partspeaker/headspeaker.jsp" %>


<div class="mb-3"><fmt:message key="presentation_editor"/></div>
<form action="${pageContext.request.contextPath}
/conference/speaker/saveeditpresentation" method="post">
    <div class="form-group">
        <label for="exampleFormControlSelect1"><fmt:message key="conference_day"/></label>
        <select class="form-control" id="exampleFormControlSelect1" name="chooseConferenceID">
            <c:forEach items="${requestScope.conferenceList}" var="conference">
                <option value="${conference.id}"
                        <c:if test="${requestScope.presentation.conference.id eq conference.id}">selected</c:if> >
                        ${conference.date} / ${conference.subject}</option>
            </c:forEach>
        </select>
    </div>
    <label for="exampleFormControlSelect2"><fmt:message key="speaker"/></label>
    <div class="form-group">
        <select class="form-control" id="exampleFormControlSelect2" name="chooseSpeakerID">
            <option value="${requestScope.presentation.author.id}" selected>
                ${requestScope.presentation.author.firstName}</option>
        </select>
    </div>
    <div class="form-group">
        <label for="inputTheme"><fmt:message key="theme"/></label>
        <div class="input-group mb-2">
            <input id="inputTheme" class="form-control" type="text" name="theme"
                   value="${requestScope.presentation.theme}">

        </div>

        <button class="btn btn-primary" type="submit"><fmt:message key="save"/></button>
        <input type="hidden" name="presentationEditId" value="${requestScope.presentation.id}">
    </div>
</form>


<%@ include file="/WEB-INF/speaker/partspeaker/tailspeaker.jsp" %>
