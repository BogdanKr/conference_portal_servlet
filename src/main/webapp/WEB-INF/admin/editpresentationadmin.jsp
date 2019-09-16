<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/admin/partadmin/headadmin.jsp" %>


<div class="mb-3">Presentation editor </div>
<form action="${pageContext.request.contextPath}/conference/speaker/saveeditpresentation" method="post">
    <div class="form-group">
        <label for="exampleFormControlSelect1">Conference date</label>
        <select class="form-control" id="exampleFormControlSelect1" name="chooseConferenceID">
            <c:forEach items="${requestScope.conferenceList}" var="conference">
                <option value="${conference.id}"
                <c:if test="${requestScope.presentation.conference.id eq conference.id}">selected</c:if> >
                        ${conference.date} / ${conference.subject}</option>
            </c:forEach>
        </select>
    </div>
    <label for="exampleFormControlSelect2">Speaker</label>
    <div class="form-group">
    <select class="form-control" id="exampleFormControlSelect2" name="chooseSpeakerID">
        <c:forEach items="${requestScope.speakerList}" var="speaker">
            <option value="${speaker.id}"
                    <c:if test="${requestScope.presentation.author.id eq speaker.id}">selected</c:if> >
                    ${speaker.firstName}</option>
        </c:forEach>
    </select>
    </div>

    <label>Presentation theme
        <input type="text" name="theme" value="${requestScope.presentation.theme}">
    </label>

    <input type="hidden" name="presentationEditId" value="${requestScope.presentation.id}">
    <button type="submit">Save</button>
</form>


<%@ include file="/WEB-INF/admin/partadmin/tailadmin.jsp" %>
