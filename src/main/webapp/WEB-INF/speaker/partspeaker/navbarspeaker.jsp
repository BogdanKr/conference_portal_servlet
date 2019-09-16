<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}
    /conference/speaker">Conference portal</a>
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
                   href="${pageContext.request.contextPath}/conference/user/conferencelist">All conferences </a>
            </li>
            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/">My registration </a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/conference/user/presentationlist">All presentation </a>
            </li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}
            /conference/user/presentationlist?speakerID=${sessionScope.user.id}">My presentation</a>
            </li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}
                /conference/user/edit">Edit my profile</a>
            </li>
        </ul>

        <div class="navbar-text mr-3"> ${sessionScope.user.firstName}</div>
        <div class="mr-3">
            <form action="${pageContext.request.contextPath}/conference/logout" method="post">
                <button type="submit" class="btn btn-primary">Log out <i class="fas fa-sign-out-alt"></i>
                </button>
            </form>
        </div>
    </div>
</nav>
