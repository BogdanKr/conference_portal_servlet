
<div class="card-columns">
    <c:forEach items="${sessionScope.conferenceList}" var="conference">
        <div class="card">
            <div class="card-header ">
                <div class="row">
                    <div class="col-5"> ${conference.date}</div>
                    <div class="col pr-0 pl-0"> ${conference.subject}</div>
                </div>
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
<%--                    <div class="col-8">--%>
<%--                        <a href="/conference/${conference.id}/like" style="color: #b10821">--%>
<%--                            <#if conference.meRegistered>--%>
<%--                            <i class="fas fa-registered"><@spring.message "registered"/> </i>--%>
<%--                            <#else>--%>
<%--                            <i class="far fa-registered"> </i>--%>
<%--                        </#if>--%>
<%--                        </a>--%>
<%--                    </div>--%>
<%--                    <div class="col-4">--%>
<%--                            ${conference.registrations}--%>
<%--                    </div>--%>
                </div>
            </div>
        </div>
    </c:forEach>
</div>