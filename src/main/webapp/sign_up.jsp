<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <meta charset="utf-8">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="x-ua-compatible" content="IE=edge">
  <title>popHealth : An Open Source Quality Measure Reference Implementation</title>
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>
</head>

<body class="user">
<div class="register-body">
  <h1>Register</h1>
  <form accept-charset="UTF-8" action="/users" class="registration" id="new_user" method="post" name="register_form"><div style="display:none"><input name="utf8" value="✓" type="hidden"><input name="authenticity_token" value="LIx7H3Uzc53NSnkRMRX5Sf4UwnrnEq4vEb2nmKl4jiU=" type="hidden"></div>
    <div class="form-group row">
      <div class="col-md-6">
        <div class="input-group input-group-lg required">
          <span class="input-group-addon"><i class="fa fa-user fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_first_name" name="user[first_name]" placeholder="first name*" type="text">
        </div>
      </div>
      <div class="col-md-6">
        <div class="input-group input-group-lg required">
          <span class="input-group-addon"><i class="fa fa-user fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_last_name" name="user[last_name]" placeholder="last name*" type="text">
        </div>
      </div>
    </div>
    <div class="form-group row">
      <div class="col-md-6">
        <div class="input-group input-group-lg required">
          <span class="input-group-addon"><i class="fa fa-user fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_username" name="user[username]" placeholder="username*" type="text">
        </div>
        <p class="help-block">Must be at least 3 characters long.</p>
      </div>
      <div class="col-md-6">
        <div class="input-group input-group-lg required">
          <span class="input-group-addon"><i class="fa fa-envelope fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_email" name="user[email]" placeholder="email@example.com*" type="email">
        </div>
      </div>
    </div>
    <div class="form-group row">
      <div class="col-md-6">
        <div class="input-group input-group-lg required">
          <span class="input-group-addon"><i class="fa fa-lock fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_password" name="user[password]" placeholder="password*" type="password">
        </div>
        <p class="help-block">Must be at least 6 characters long.</p>
      </div>
      <div class="col-md-6">
        <div class="input-group input-group-lg required">
          <span class="input-group-addon"><i class="fa fa-lock fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_password_confirmation" name="user[password_confirmation]" placeholder="confirm password*" type="password">
        </div>
      </div>
    </div>
    <div class="form-group row">
      <div class="col-md-6">
        <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-user fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_company" name="user[company]" placeholder="company" type="text">
        </div>
      </div>
      <div class="col-md-6">
        <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-link fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_company_url" name="user[company_url]" placeholder="www.company.com" type="text">
        </div>
      </div>
    </div>
    <div class="form-group row">
      <div class="col-md-6">
        <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-h-square fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_registry_name" name="user[registry_name]" placeholder="registry name" type="text">
        </div>
      </div>
      <div class="col-md-6">
        <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-archive fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_registry_id" name="user[registry_id]" placeholder="registry identifier" type="text">
        </div>
      </div>
    </div>
    <div class="form-group row">
      <div class="col-md-6">
        <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-hospital-o fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_npi" name="user[npi]" placeholder="national provider id" type="text">
        </div>
      </div>
      <div class="col-md-6">
        <div class="input-group input-group-lg">
          <span class="input-group-addon"><i class="fa fa-folder fa-lg fa-fw"></i></span>
          <input class="form-control" id="user_tin" name="user[tin]" placeholder="tax id number" type="text">
        </div>
      </div>
    </div>

      <div class="col-md-6">
        <div class="row">
        <div class="col-xs-6"><a class="btn btn-default btn-block btn-lg" href="/">Cancel</a></div>
        <div class="col-xs-6"><input class="btn btn-primary btn-block btn-lg" name="commit" value="REGISTER" type="submit"></div>
        </div>
      </div>
    </div>
</form></div>
</body></html>