package com.dheeti.beat.wrapper.servlet;

import com.dheeti.beat.wrapper.common.StringConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by jayram on 17/3/15.
 */
public class ExecTaskServlet extends HttpServlet implements StringConstants{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        HashMap<String,String> params = extractParameters(request,request.getParameterNames());
        String taskCommand = this.createTaskCommand(params);
        String shellString = "/bin/sh";
        String shellParam = "-c";
        String[] cmd = {shellString,shellParam,taskCommand};

        Runtime runtime = Runtime.getRuntime();
        //String[] cmd = { "/bin/sh", "-c", "cd /home/jayram/beat/popHealth; rake bundle:import[/home/jayram/beat/testdata/bundle_0314.zip] RAILS_ENV=development" };

        Process process = runtime.exec(cmd);
        try {

            DataInputStream in = new DataInputStream(process.getInputStream());

            String msg = "Task Executed Successfully with following log \n";
            String line;
            while ((line = in.readLine()) != null) {
                msg = msg + "\n" + line;
            }
            //out.println(msg);
            in.close();
            out.close();
            process.destroy();
            //request.setAttribute("Log", msg);
            RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher("tasks");
            dispatcher.forward(request,response);
        }
        catch (Exception e) {
            out.println("Problem with command: " +
                    e.getStackTrace().toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
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
