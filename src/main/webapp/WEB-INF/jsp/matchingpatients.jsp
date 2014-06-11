<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.dheeti.beat.wrapper.Patients" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matching Patients</title>
</head>
<body>
<table>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Select</th>
</table>
<%
    HashMap model = (HashMap)request.getAttribute("model");
    ArrayList<HashMap<String,Object>> patientsList = (ArrayList)model.get("patients");
    String hqmf_id = (String)model.get("hqmf_id");
    for(HashMap<String,Object> patientMap : patientsList){
        String link = "/patients/"+ patientMap.get("id") +"/measure/"+hqmf_id;
    %>
<TR>
    <TD><%=(String)patientMap.get("first")%></TD>
    <TD><%=(String)patientMap.get("last")%></TD>
    <TD><a href="<%=link%>">Select</a></TD>
</TR>
<%}%>
</body>
</html>