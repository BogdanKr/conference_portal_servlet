
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal${conference.id}">
    <c:if test="${!requestScope.containsKey('edit')}">Add conference </c:if>
    <c:if test="${requestScope.containsKey('edit')}">Edit</c:if>
</button>
<!-- Modal -->
<div class="modal fade bd-example-modal-lg" id="modal${conference.id}" tabindex="-1" role="dialog" aria-labelledby="modalLabel3"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">

                <%@ include file="/WEB-INF/admin/partadmin/datePickeradmin.jsp" %>

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
