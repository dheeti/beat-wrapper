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
import java.util.Map;

/**
 * Created by jayram on 17/3/15.
 */
public class ExecTaskServlet extends HttpServlet implements StringConstants{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        /*HashMap<String,String> params = extractParameters(request,request.getParameterNames());
        String taskCommand = this.createTaskCommand(params);
        String shellString = "/bin/sh";
        String shellParam = "-c";*/
        //String[] cmd = {shellString,shellParam,taskCommand};

        Runtime runtime = Runtime.getRuntime();

        String[] command1 = {"/bin/bash", "-c", "source /etc/profile.d/rvm.sh;rvm use 2.1.2;rvm --default 2.1.2; rake bundle:import[/home/jayram/beat/testdata/bundle_0314.zip] RAILS_ENV=development"};


        ProcessBuilder probuilder = new ProcessBuilder(command1);
        probuilder.command(command1);
        Map<String,String> env = probuilder.environment();

        env.put("PATH","/sbin:/bin:/usr/sbin:/usr/bin:/usr/local/rvm/gems/ruby-2.1.2/bin:/usr/local/rvm/gems/ruby-2.1.2@global/bin:/usr/local/rvm/rubies/ruby-2.1.2/bin");
        //env.put("BUNDLE_BIN_PATH","/usr/local/rvm/gems/ruby-2.1.2/gems/bundler-1.7.7/bin/bundle");
        //env.put("BUNDLE_GEMFILE","/home/jayram/beat/popHealth/Gemfile");
        //env.put("RUBYLIB","/usr/local/rvm/gems/ruby-2.1.2/gems/bundler-1.7.7/lib");
        //env.put("RUBYOPT","-rbundler/setup");
        //env.put("rvm_bin_path","/usr/local/rvm/bin");
        env.put("GEM_HOME","/usr/local/rvm/gems/ruby-2.1.2");
        //env.put("IRBRC","/usr/local/rvm/rubies/ruby-2.1.2/.irbrc");
        //env.put("MY_RUBY_HOME","usr/local/rvm/rubies/ruby-2.1.2");
        //env.put("rvm_path","usr/local/rvm");
        //env.put("rvm_prefix","/usr/local");
        env.put("GEM_PATH","/usr/local/rvm/gems/ruby-2.1.2:/usr/local/rvm/gems/ruby-2.1.2@global");
        //env.put("RUBY_VERSION","ruby-2.1.2");

        probuilder.directory(new File("/home/jayram/beat/popHealth"));

        Process process = probuilder.start();
        try {

            DataInputStream in = new DataInputStream(process.getInputStream());

            String msg = "Task Executed Successfully with following log \n";
            String line;
            while ((line = in.readLine()) != null) {
                msg = msg + "\n" + line;
                System.out.println("Inside While..........."+msg);
            }
            out.println(msg);
            //in.close();
            //out.close();
            //process.destroy();
            //request.setAttribute("Log", msg);
            //RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher("tasks");
            //dispatcher.forward(request,response);
        }
        catch (Exception e) {
            out.println("Problem with command: " +
                    e.getStackTrace().toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private String createTaskCommanlsd(HashMap<String, String> params) {
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