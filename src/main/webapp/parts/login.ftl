<#macro login path isRegisterForm logOrReg>

    <form action="" method="post">
        <#if isRegisterForm>
        <div class="form-group row">
            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="customSwitch1" name="active">
                <label class="custom-control-label" for="customSwitch1" >Speaker?</label>
            </div>
        </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> First name </label>
                <div class="col-sm-4">
                    <input type="text" name="firstName" class="form-control" placeholder="First name" required/>
                </div>
            </div>
        </#if>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email </label>
            <div class="col-sm-4">
                <input type="email" name="email" class="form-control" placeholder="Email" required autofocus/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password </label>
            <div class="col-sm-4 ">
                <input type="password" name="password" class="form-control" placeholder="Password" required/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">
            <#if !isRegisterForm>Login<#else >Registration</#if>
        </button>
    </form>

    <div class="mt-1">
        <#if !isRegisterForm><a href="/">Add user</a></#if>
    </div>
</#macro>



<#macro logout>
    <form action="/" method="post">
        <button type="submit" class="btn btn-primary">Log out  <i class="fas fa-sign-out-alt"></i>
        </button>
    </form>
</#macro>

<%@ page import="java.util.*, java.text.*" %>