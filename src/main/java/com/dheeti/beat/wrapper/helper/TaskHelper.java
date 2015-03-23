package com.dheeti.beat.wrapper.helper;

import com.dheeti.beat.wrapper.common.StringConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayram on 19/3/15.
 */
public class TaskHelper implements StringConstants{
    public  String execute(HttpServletRequest request, HashMap<String,String> params){
        String msg = null;
        String taskCommand = this.createTaskCommand(params);
        String shellString = SHELL_STRING;
        String shellParam = SHELL_PARAM;
        String[] cmd = {shellString,shellParam,taskCommand};
        Runtime runtime = Runtime.getRuntime();
        ProcessBuilder probuilder = new ProcessBuilder(cmd);
        probuilder.directory(new File((String)request.getSession().getServletContext().getAttribute(LOCAL_POPHEALTH_DIR)));
        Map<String,String> env = probuilder.environment();
        String path = env.get("PATH")+":";

        env.put("PATH",path+RAKE_DIR+":"+RAILS_DIR);///usr/local/rvm/gems/ruby-2.1.2/bin:/usr/local/rvm/rubies/ruby-2.1.2/bin");

        try {
            Process process = probuilder.start();
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
            rakeTask = new StringBuffer("source /etc/profile.d/rvm.sh;rvm use 2.1.2;rvm --default 2.1.2; rake ");
            //rakeTask.append(LOCAL_POPHEALTH_DIR).append("; rake ");
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
