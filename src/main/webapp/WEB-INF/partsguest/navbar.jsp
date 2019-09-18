<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/"><fmt:message key="title"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">
                    <i class="fas fa-home"></i>
                </a>
            </li>
        </ul>
        <div class="navbar-text mr-3"></div>
        <a href="?lang=ua">
            <img alt="Українська" height="32" src="/img/UA.ico" title="Ukrainian" width="32">
        </a>
        <a href="?lang=en">
            <img alt="Англійська" height="32" src="/img/United-Kingdom.ico" title="English" width="32">
        </a>
    </div>
</nav>
