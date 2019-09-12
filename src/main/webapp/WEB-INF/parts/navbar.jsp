<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" <c:if test="${sessionScope.role eq 'ADMIN'}">href="${pageContext.request.contextPath}/conference/admin"</c:if>
       <c:if test="${sessionScope.role eq 'SPEAKER'}">href="${pageContext.request.contextPath}/conference/speaker" </c:if>
       <c:if test="${sessionScope.role eq 'USER'}">href="${pageContext.request.contextPath}/conference/user" </c:if>
       <c:if test="${sessionScope.role eq null}">href="${pageContext.request.contextPath}/" </c:if>
    >Conference portal</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link"
                   <c:if test="${sessionScope.role eq 'ADMIN'}">href="${pageContext.request.contextPath}/conference/admin"</c:if>
                   <c:if test="${sessionScope.role eq 'SPEAKER'}">href="${pageContext.request.contextPath}/conference/speaker" </c:if>
                   <c:if test="${sessionScope.role eq 'USER'}">href="${pageContext.request.contextPath}/conference/user" </c:if>
                   <c:if test="${sessionScope.role eq null}">href="${pageContext.request.contextPath}/" </c:if>>
                    <i class="fas fa-home"></i>
                </a>
            </li>
            <c:if test="${sessionScope.userEmail ne null}">
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/conference/user/conferencelist">All conferences </a>
                </li>
                <li class="nav-item"><a class="nav-link"
                                        href="${pageContext.request.contextPath}/">My registration </a>
                </li>
                <li class="nav-item"><a class="nav-link"
                                        href="${pageContext.request.contextPath}/">All presentation </a>
                </li>
                <c:if test="${sessionScope.role eq 'SPEAKER'}">
                    <li class="nav-item"><a class="nav-link"
                                            href="${pageContext.request.contextPath}/">My presentation</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role ne 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/conference/user/edit">
                            Edit my profile</a>
                    </li>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.role eq 'ADMIN'}">
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/conference/admin/edit?id=${sessionScope.user.id}">Edit
                        my profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/conference/admin/userlist">User
                        list </a>
                </li>
            </c:if>
        </ul>

        <div class="navbar-text mr-3"> ${sessionScope.user.firstName}</div>

        <c:if test="${sessionScope.userEmail ne null}">
            <div class="mr-3">
                <form action="${pageContext.request.contextPath}/conference/logout" method="post">
                    <button type="submit" class="btn btn-primary">Log out <i class="fas fa-sign-out-alt"></i>
                    </button>
                </form>
            </div>
        </c:if>
    </div>
</nav>
