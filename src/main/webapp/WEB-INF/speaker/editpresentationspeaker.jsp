<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/parts/head.jsp" %>


<div class="mb-3">Presentation editor for ${requestScope.conference.date} / ${requestScope.conference.subject}</div>
<form action="${pageContext.request.contextPath}/conference/speaker/editpresentation" method="post">
    <div class="form-group">
        <label for="exampleFormControlSelect1">Conference date</label>
        <select class="form-control" id="exampleFormControlSelect1" name="date">
            <c:forEach items="${requestScope.conferenceList}" var="conference">
                <option>${conference.date}</option>
            </c:forEach>
        </select>
    </div>
    <label>Speaker
        <input type="text" name="firstName" readonly
        <c:choose>
        <c:when test="${requestScope.presentation ne null}">
               value=" ${requestScope.presentation.author.firstName}"
        </c:when>
        <c:when test="${requestScope.presentation eq null}">
               value="${sessionScope.user.firstName}"
        </c:when>
        </c:choose>
        >
    </label>
    <label>Presentation theme
        <input type="text" name="theme" value="${requestScope.presentation.theme}">
    </label>

    <input type="hidden" name="presentationEditId" value="${requestScope.presentation.id}">
    <input type="hidden" name="presentationConfId" value="${requestScope.conference.id}">
    <button type="submit">Save</button>
</form>


<%@ include file="/WEB-INF/parts/tail.jsp" %>
