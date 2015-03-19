package com.dheeti.beat.wrapper.helper;

import com.dheeti.beat.wrapper.common.StringConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by jayram on 19/3/15.
 */
public class TaskHelper implements StringConstants{
    public  String execute(HttpServletRequest request, HashMap<String,String> params){
        String msg = null;
        //HashMap<String,String> params = extractParameters(request,request.getParameterNames());
        String taskCommand = this.createTaskCommand(params);
        String shellString = "/bin/sh";
        String shellParam = "-c";
        String[] cmd = {shellString,shellParam,taskCommand};
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(cmd);
            DataInputStream in = new DataInputStream(process.getInputStream());
            msg = "Task Executed Successfully with following log \n";
            String line;
            while ((line = in.readLine()) != null) {
                msg = msg + "\n" + line;
            }
            in.close();
            process.destroy();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    private String createTaskCommand(HashMap<String, String> params) {
        StringBuffer rakeTask = null;
        if(params.get("tasktype").equalsIgnoreCase(TASK_TYPE_RAKETASK)){
            rakeTask = new StringBuffer("cd ");
            rakeTask.append(LOCAL_POPHEALTH_DIR).append("; rake ");
            rakeTask.append(params.get("command"));
            if((params.get("taskparams")) != null &&  !params.get("taskparams").equals("")) {
                rakeTask.append("[");
                rakeTask.append(params.get("taskparams"));
                rakeTask.append("]");
            }
            rakeTask.append(" RAILS_ENV=");
            rakeTask.append(params.get("environment"));
        }
        return rakeTask.toString();
    }

    private HashMap<String, String> extractParameters(HttpServletRequest request,
                                                      Enumeration parameterNames) {
        HashMap<String, String> params = new HashMap<String, String>();
        while(parameterNames.hasMoreElements()){
            String paramName = (String)parameterNames.nextElement();
            params.put(paramName,request.getParameter(paramName));
        }
        return params;
    }
}
