<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css"/>
<link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css"/>
<style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form {
    font-family: Arial, Helvetica, sans-serif;
    color: black
}

.bootstrap-iso form button, .bootstrap-iso form button:hover {
    color: white !important;
}

.asteriskField {
    color: red;
}

.bootstrap-iso .form-group {
    margin-bottom: 5px;
}</style>

<div class="bootstrap-iso">
    <div class="container-fluid">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <form class="form-horizontal" method="post"
                  action="${pageContext.request.contextPath}/conference/admin/addconference">
                <div class="form-group form-group-sm">
                    <label class="control-label  requiredField" for="localDate${conference.id}">
                        <fmt:message key="date"/>
                        <span class="asteriskField"> * </span>
                    </label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar">
                            </i>
                        </div>
                        <input autocomplete="off" class="form-control" id="localDate${conference.id}" name="localDate"
                               placeholder="yyyy-mm-dd" type="text"
                               required value="${conference.date}"/>
                    </div>
                    <label class="control-label requiredField" for="subject">
                        <fmt:message key="subject"/>
                        <span class="asteriskField"> * </span>
                    </label>
                    <input class="form-control" id="subject" name="subject" type="text" required
                           value="${conference.subject}"/>
                </div>
                <div class="form-group mb-1">
                    <input type="hidden" name="conferenceEditId" value="${conference.id}"/>
                    <button class="btn btn-primary btn-sm" name="submit" type="submit">
                        <fmt:message key="save"/>
                    </button>
                    <c:if test="${requestScope.containsKey('edit')}">
                        <a class="btn btn-danger btn-sm"
                           href="${pageContext.request.contextPath}
                           /conference/admin/delete_conference?confId=${conference.id}"
                           role="button"><fmt:message key="delete"/></a>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<script>
    $(document).ready(function () {
        var date_input = $('input[id="localDate${conference.id}"]'); //our date input has the name "localDate"
        var container = $('.bootstrap-iso${conference.id} form').length > 0 ? $('.bootstrap-iso${conference.id} form').parent() : "body";
        date_input.datepicker({
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true
        })
    })
</script>