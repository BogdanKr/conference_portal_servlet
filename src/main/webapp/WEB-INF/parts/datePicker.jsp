<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css"/>
<link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css"/>
<style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form {
        font-family: Arial, Helvetica, sans-serif;
        color: black}
    .bootstrap-iso form button, .bootstrap-iso form button:hover {
        color: white !important;}
    .asteriskField {
        color: red;}
    .bootstrap-iso .form-group {
        margin-bottom: 5px;
    }</style>

<div class="bootstrap-iso">
    <div class="container-fluid">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/conference/admin/addconference">
                    <div class="form-group form-group-sm">
                        <label class="control-label  requiredField" for="localDate">
                            Date
                            <span class="asteriskField"> * </span>
                        </label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar">
                                    </i>
                                </div>
                                <input class="form-control" id="localDate" name="localDate" placeholder="yyyy-mm-dd" type="text"
                                       required value=""/>
                            </div>
                        <label class="control-label requiredField" for="subject">
                            Subject
                            <span class="asteriskField"> * </span>
                        </label>
                            <input class="form-control" id="subject" name="subject" type="text" required
                                   value=""/>
                    </div>
                    <div class="form-group mb-1">
                            <input type="hidden" name="id" value=""/>
                            <button class="btn btn-primary btn-sm" name="submit" type="submit">
                                Add conference
                            </button>
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
        var date_input = $('input[name="localDate"]'); //our date input has the name "localDate"
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            // language: "ru",
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
</script>