package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.mongodb.MongoDAO;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jayramj on 31/5/14.
 */
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("************Inside Sign In Servlet");
        //MongoDAO client = new MongoDAO();
        //boolean success = client.executeLoginQuery((String)request.getAttribute("userName"),(String)request.getAttribute("password"));
        request.getSession().setAttribute("userName",(String)request.getParameter("userName"));
        request.getSession().setAttribute("password",(String)request.getParameter("password"));
        response.sendRedirect("/dashboard.jsp");
    }
}
