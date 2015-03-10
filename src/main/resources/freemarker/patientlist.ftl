<html>
<head>
    <title>Matching Patients</title>
    <style type="text/css"></style>
</head>
<body>
<table>
    <th>Select</th>
    <th>First Name</th>
    <th>Last Name</th>


<#list .data_model?keys as key>
<#assign patientMap = .data_model[key]>
    <TR>
        <TD><p><input type="radio" name="selectPatient" value=${key}></p></TD>
        <TD>${patientMap.first}
        </TD>
        <TD>${patientMap.last}
        </TD>
    </TR>
</#list>
</table>
</form>
</body>
</html>
