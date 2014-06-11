<%--
  Created by IntelliJ IDEA.
  User: jayramj
  Date: 10/6/14
  Time: 8:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Search</title>
</head>
<body>
<form method="post" action="patients/search">
    <p><input type="text" name="firstname" value="" placeholder="FirstName"></p>
    <p><input type="text" name="lastname" value="" placeholder="LastName"></p>
    <p><input type="hidden" name="hqmf_id" value="<%= request.getParameter("measure") %>"> </p>
    <p class="submit"><input type="submit" name="search" value="Search"></p>
</form>
</body>
</html>
