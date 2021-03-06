<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}
    /conference/speaker"><fmt:message key="title"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}
                /conference/speaker"> <i class="fas fa-home"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}
                   /conference/speaker/conferencelist"><fmt:message key="all_conferences"/></a>
            </li>
            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}
                                    /conference/speaker/myconferenceregistration"><fmt:message key="my_registrations"/> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}
                   /conference/speaker/presentationlist"><fmt:message key="all_presentations"/> </a>
            </li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}
            /conference/user/presentationlist?speakerID=${sessionScope.user.id}"><fmt:message key="my_presentations"/></a>
            </li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}
                /conference/speaker/edit"><fmt:message key="edit_profile"/></a>
            </li>
        </ul>

        <div class="navbar-text mr-3"> ${sessionScope.user.firstName}</div>
        <a href="?lang=ua">
            <img alt="Українська" height="32" src="/img/UA.ico" title="Ukrainian" width="32">
        </a>
        <a href="?lang=en">
            <img alt="Англійська" height="32" src="/img/United-Kingdom.ico" title="English" width="32">
        </a>
        <div class="mr-3">
            <form action="${pageContext.request.contextPath}
            /conference/logout" method="post">
                <button type="submit" class="btn btn-primary"><fmt:message key="logout"/><i class="fas fa-sign-out-alt"></i>
                </button>
            </form>
        </div>
    </div>
</nav>
