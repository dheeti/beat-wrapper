<html>
<head>
    <title>Beat Configurations</title>
    <style type="text/css"></style>
</head>
<body>
<html>
<head>
    <title>Task Configurations</title>
    <style type="text/css"></style>
</head>
<body>
<form taskId = "form-taskId" method="post" action="tasks">
    <table>
        <th></th>
        <th>Name</th>
        <th>Type</th>
        <th>Command</th>
        <th>Parameters</th>
        <th>Environment</th>
        <th>Description</th>
        <th>Run</th>
    <#list model as task>
        <TR>
            <TD><input type="hidden" name="taskid" value="${task.taskId}">

            </TD>
            <TD><input type="text" name="taskname" value="${task.taskname}">
            </TD>
            <TD><input type="text" name="tasktype" value="${task.tasktype}">
            </TD>
            <TD><input type="text" name="command" value="${task.command}">
            </TD>
            <TD><input type="text" name="parameters" value="${task.parameters!""}">
            </TD>
            <TD><input type="text" name="environment" value="${task.environment}">
            </TD>
            <TD><textarea rows="4" cols="25" name="description">${task.description}</textarea>
            </TD>
            <TD><a href="tasks/run/${task.taskId}">Run</a>
            </TD>
        </TR>
    </#list>
    </table>
    <p class="submit"><input type="submit" name="Save" value="Save"></p>
</form>
</body>
</html>

