package com.dheeti.beat.wrapper.servlet;


import com.dheeti.beat.wrapper.common.StringConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by jayramj on 20/8/14.
 */
public class StartupServlet extends HttpServlet implements StringConstants{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void init()throws ServletException {
        ServletContext sc = getServletContext();
        Properties startupProperties = new Properties();
        try {
            startupProperties.load(sc.getResourceAsStream(WEB_INF_STARTUP_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.setAttribute(pophealthip,startupProperties.getProperty(pophealthip));
        sc.setAttribute(pophealthip,startupProperties.getProperty(bonnieip));
    }
}