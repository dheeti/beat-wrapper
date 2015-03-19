<html>
<head>
    <title>Task Configurations</title>
</head>
<body>
<form method="POST" action="/tasks/exec">
    <input type="hidden" name="taskid" value="${model.taskId}">

    <p>Name :<input type="text" name="taskname" value="${model.taskname}"></p>

    <p>Type :<input type="text" name="tasktype" value="${model.tasktype}"></p>

    <p>Command :<input type="text" name="command" value="${model.command}"></p>

    <p>Parameters :<input type="text" name="taskparams" value="${model.parameters!""}"></p>

    <p>Environment :<input type="text" name="environment" value="${model.environment}"></p>

    <p>Description :<textarea rows="4" cols="25" name="description">${model.description}</textarea></p>

    <p class="submit"><input type="submit" name="Execute" value="Execute"></p>
</form>
</body>
</html>