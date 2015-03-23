<html>
<head>
    <title>Beat Configurations</title>
    <style type="text/css"></style>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../css/beat.css" rel="stylesheet">
</head>
<body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../js/bootstrap.min.js"></script>
<H1> Please be careful when you change these values they have a system wide impact.</H1>
<form id = "form-id" method="post" action="system">
    <div class="container" style="border: solid 1px black; padding: 0px">
    <div class"row" style="background-color: #f0f0f0">
        <div></div>
        <div class="col-md-6">Configuration Key</div>
        <div class="col-md-6">Configuration Value</div>
    </div>

    <#list model as config> 
        <div class="row">
            <div><input type="hidden" name="id" value="${config.id}">

            </div>
            <div  class="col-md-6"><input class="col-md-6" readonly="true" type="text" name="configkey" value="${config.key}">
            </div  class="col-md-6">
            <div class="col-md-6"><input class="col-md-6" type="text" name="configvalue" value="${config.value}">
            </div>
        </div>
    </#list>
    </div>
    <div class="row">
    <p class="submit" ><input id="btnsubmit" type="submit" name="Save" value="Save"></p>
    </div>
</form>
</body>
</html>
