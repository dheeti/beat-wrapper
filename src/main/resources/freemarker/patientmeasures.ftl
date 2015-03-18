<html>
<head>
    <title>Select Measure To Match</title>
    <style type="text/css"></style>
</head>
<body>

<form id = "form-id" method="post" action="patients">
    <h2>Select Measure to Match</h2>
    Patient : ${patientId}
    <input type="hidden" value=${patientId}>
    <table>
        <th></th>
        <th>CMS Id</th>
        <th>Name</th>
        <th></th>
    <#list measures as measure>
        <TR>
            <TD><input type="hidden" value="${measure.hqmf_id}"/></TD>
            <TD>${measure.cms_id}</TD>
            <TD>${measure.name}</TD>
            <TD><a href="${patientId}/measures/${measure.hqmf_id}/exec}"> Execute</a> </TD>
        </TR>
    </#list>
    </table>
</form>

</body>
</html>
