package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.common.StringConstants;
import com.dheeti.beat.wrapper.helper.ReloadServletContextHelper;
import com.dheeti.beat.wrapper.persistence.Configuration;
import com.dheeti.beat.wrapper.persistence.DAO.ConfigurationDAO;
import com.dheeti.beat.wrapper.persistence.DAO.TaskDAO;
import com.dheeti.beat.wrapper.persistence.RakeTask;
import com.dheeti.beat.wrapper.persistence.Task;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

@Path("tasks")
public class Tasks implements StringConstants {
    @Context
    private HttpServletRequest request;

    @GET
    @Path("/")
    @Produces("text/html")
    public Viewable getTaskConfigurations() {
        List<Task> model = new ArrayList<Task>();
        TaskDAO taskDAO = new TaskDAO();
        List<Task> taskList = taskDAO.getTaskConfigurations();
        for(Task task : taskList){
            model.add(task);
        }
        return new Viewable("/taskconfig.ftl",model);
    }

    @POST
    @Path("/")
    @Produces("text/html")
    public String saveTaskConfigurations(@FormParam("taskid") List<String> taskIdList,@FormParam("taskname")List<String> tasknameList,
                                         @FormParam("tasktype")List<String> tasktypeList,@FormParam("command")List<String> commandList,
                                         @FormParam("parameters")List<String> parametersList,@FormParam("environment")List<String> environmentList,
                                         @FormParam("description")List<String> descriptionList){

        List<Task> updatedList = new ArrayList<Task>();
        int index = 0;

        for(String taskid : taskIdList){
            Task task = null;
            if(tasktypeList.get(index).equals(TASK_TYPE_RAKETASK)){
                RakeTask raketask = new RakeTask();
                raketask.setTasktype(TASK_TYPE_RAKETASK);
                raketask.setEnvironment(environmentList.get(index));
                task = raketask;
            }
            task.setTaskId(new Integer(taskid));
            task.setTaskname(tasknameList.get(index));
            task.setTasktype(tasktypeList.get(index));
            task.setCommand(commandList.get(index));
            task.setParameters(parametersList.get(index));
            task.setDescription(descriptionList.get(index));
            updatedList.add(task);
            index++;
        }
        TaskDAO taskDAO = new TaskDAO();
        boolean result = taskDAO.save(updatedList);
        String message = "Task Configuration Changes Saved Successfully";
        return message;
    }

}
