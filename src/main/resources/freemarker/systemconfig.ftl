<html>
<head>
    <title>Beat Configurations</title>
    <style type="text/css"></style>
</head>
<body>
<H1> Please be careful when you change these values they have a system wide impact.</H1>
<form id = "form-id" method="post" action="system">
    <table>
        <th></th>
        <th>Configuration Key</th>
        <th>Configuration Value</th>


    <#list model as config>
        <TR>
            <TD><input type="hidden" name="id" value="${config.id}">

            </TD>
            <TD><input readonly="true" type="text" name="configkey" value="${config.key}">
            </TD>
            <TD><input type="text" name="configvalue" value="${config.value}">
            </TD>
        </TR>
    </#list>
    </table>
    <p class="submit"><input type="submit" name="Save" value="Save"></p>
</form>
</body>
</html>
