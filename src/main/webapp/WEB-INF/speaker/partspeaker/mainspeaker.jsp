<%@ include file="/WEB-INF/speaker/partspeaker/pagerspeaker.jsp" %>

<div class="card-columns">
    <c:forEach items="${sessionScope.conferenceList}" var="conference">
        <div class="card">
            <div class="card-header ">
                <div class="row">
                    <div class="col-5"> ${conference.date}</div>
                    <div class="col pr-0 pl-0"> ${conference.subject}</div>
                </div>
                <div class="col-md-auto">
                    <a href="${pageContext.request.contextPath}
                        /conference/speaker/addpresentation?conf=${conference.id}"> <fmt:message key="add_presentation"/></a>
                </div>
            </div>

            <div class="card-body ">
                <table class="table table-sm">
                    <thead>
                    <tr>
                        <th><fmt:message key="speaker"/></th>
                        <th style="padding-left: 10px"><fmt:message key="theme"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody style="color: #781a20">
                    <c:forEach items="${conference.presentations}" var="presentation">
                        <tr>
                            <td>${presentation.author.firstName}</td>
                            <td>${presentation.theme}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="card-footer ">
                <div class="row">
                    <div class="col-8">
                        <a href="${pageContext.request.contextPath}
                        /conference/speaker/conference_registration?confId=${conference.id}" style="color: #b10821">
                            <c:if test="${conference.currentUserRegistered}">
                                <i class="fas fa-registered"><fmt:message key="registered"/> </i>
                            </c:if>
                            <c:if test="${!conference.currentUserRegistered}">
                                <i class="far fa-registered"> </i>
                            </c:if>
                        </a>
                    </div>
                    <div class="col-4">
                        <fmt:message key="total"/> ${conference.userRegistrations.size()}
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<%@ include file="/WEB-INF/speaker/partspeaker/pagerspeaker.jsp" %>