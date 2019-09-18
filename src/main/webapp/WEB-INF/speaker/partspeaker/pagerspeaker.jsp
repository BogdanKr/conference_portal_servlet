<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="${pageContext.request.contextPath}
    /conference/speaker?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="${pageContext.request.contextPath}
                    /conference/speaker?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="${pageContext.request.contextPath}
    /conference/speaker?page=${currentPage + 1}">Next</a></td>
</c:if>