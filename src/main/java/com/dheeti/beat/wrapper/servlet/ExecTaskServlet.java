package com.dheeti.beat.wrapper.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.DataInputStream;

/**
 * Created by jayram on 17/3/15.
 */
public class ExecTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String command0 = "cd ";
        String argo = "/home/jayram/beat/popHealth";
        String command1 = "pwd";

        Runtime runtime = Runtime.getRuntime();
        String[] cmd = { "/bin/sh", "-c", "cd /home/jayram/beat/popHealth; rake bundle:import[/home/jayram/beat/testdata/bundle_0314.zip] RAILS_ENV=development" };

        Process process = runtime.exec(cmd);
        try {

            DataInputStream in = new DataInputStream(process.getInputStream());

            String msg = "";
            String line;
            while ((line = in.readLine()) != null) {
                msg = msg + "\n" + line;
            }
            out.println(msg);
            in.close();
            out.close();
            process.destroy();
        }
        catch (Exception e) {
            out.println("Problem with command: " +
                    e.getStackTrace().toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
