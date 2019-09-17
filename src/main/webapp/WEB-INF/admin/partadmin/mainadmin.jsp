<c:if test="${sessionScope.role eq 'ADMIN'}">
    <%@ include file="/WEB-INF/admin/partadmin/addconfmodal.jsp" %>
</c:if>

<div class="card-columns">
    <c:forEach items="${sessionScope.conferenceList}" var="conference">
        <div class="card">
            <div class="card-header ">
                <div class="row">
                    <div class="col-5"> ${conference.date}</div>
                    <div class="col pr-0 pl-0"> ${conference.subject}</div>
                </div>
                <c:if test="${sessionScope.role eq 'SPEAKER'}">
                    <div class="col-md-auto">
                        <a href="${pageContext.request.contextPath}
                        /conference/speaker/addpresentation?conf=${conference.id}"> Add presentation</a>
                    </div>
                </c:if>
            </div>

            <div class="card-body ">
                <table class="table table-sm">
                    <thead>
                    <tr>
                        <th>Speaker</th>
                        <th style="padding-left: 10px">Theme</th>
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
                        /conference/admin/conference_registration?confId=${conference.id}" style="color: #b10821">
                            <c:if test="${conference.currentUserRegistered}">
                                <i class="fas fa-registered">I'm registered </i>
                            </c:if>
                            <c:if test="${!conference.currentUserRegistered}">
                            <i class="far fa-registered"> </i>
                            </c:if>
                        </a>
                    </div>
                    <div class="col-4">
                            ${conference.userRegistrations.size()}
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>