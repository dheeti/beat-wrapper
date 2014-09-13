<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.dheeti.beat.wrapper.Patients" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matching Patients</title>
    <style type="text/css"></style>
</head>
<body>
<%
    HashMap model = (HashMap) request.getAttribute("model");
    ArrayList<HashMap<String, Object>> patientsList = (ArrayList) model.get("patients");
    String hqmf_id = (String) model.get("hqmf_id");

    String action = "/measures/0105/patients";
%>
<form method="post" action="<%=action%>">
    <table>
        <th>Select</th>
        <th>First Name</th>
        <th>Last Name</th>

        <%
            for (HashMap<String, Object> patientMap : patientsList) {
        %>
        <TR>
            <TD><p><input type="radio" name="selectPatient" value="<%=(String)patientMap.get("id")%>"></p></TD>
            <TD><%=(String) patientMap.get("first")%>
            </TD>
            <TD><%=(String) patientMap.get("last")%>
            </TD>
        </TR>
        <%}%>
    </table>
    <p><input type="submit" name="AddPatient" value="AddPatient"></p>
</form>
</body>
</html>
