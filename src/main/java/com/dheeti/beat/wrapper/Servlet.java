package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.common.StringConstants;
import com.dheeti.beat.wrapper.helper.EncryptUtil;
import com.dheeti.beat.wrapper.mongodb.MongoDAO;
import com.mongodb.MongoClient;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jayramj on 31/5/14.
 */
public class Servlet extends javax.servlet.http.HttpServlet implements StringConstants{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("************Inside Sign In Servlet");
        ServletContext sc = request.getSession().getServletContext();
        MongoDAO client = new MongoDAO((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),(String)sc.getAttribute(POPHEALTH_MONGO_PORT),(String)sc.getAttribute(POPHEALTH_MONGO_DB));
        String  encryptedPassword = client.executeLoginQuery((String)request.getParameter("userName"),(String)request.getParameter("password"));
        boolean matched = EncryptUtil.comparePassword(encryptedPassword,(String)request.getParameter("password"));
        System.out.println("************Matched:"+matched);
        request.getSession().setAttribute("userName",(String)request.getParameter("userName"));
        request.getSession().setAttribute("password",(String)request.getParameter("password"));
        response.sendRedirect("/dashboard.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
