<html>
<head>
    <title>Matching Patients</title>
    <style type="text/css"></style>
</head>
<body>

<form id = "form-id" method="post" action="patients">
<table>
    <th>First Name</th>
    <th>Last Name</th>


<#list .data_model?keys as key>
<#assign patientMap = .data_model[key]>
    <TR>
        <TD>${patientMap.first}
        </TD>
        <TD>${patientMap.last}
        </TD>
        <TD><a href="${key}/measures"> Run Measures</a> </TD>
    </TR>
</#list>
</table>
</form>
</body>
</html>
