<!-- Bootstrap CSS -->
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"--%>
<%--      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">--%>

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

                <%@ include file="datePicker.jsp" %>

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
