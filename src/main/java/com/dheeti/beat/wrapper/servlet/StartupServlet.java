package com.dheeti.beat.wrapper.servlet;


import com.dheeti.beat.wrapper.common.StringConstants;
import com.dheeti.beat.wrapper.persistence.Configuration;
import com.dheeti.beat.wrapper.persistence.DAO.ConfigurationDAO;
import com.dheeti.beat.wrapper.persistence.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        List<Configuration> configurationList = configurationDAO.getConfigurations(CONF_TYPE_SYSTEM);
        for(Configuration conf : configurationList){
            sc.setAttribute(conf.getKey(),conf.getValue());
        }
    }
}
