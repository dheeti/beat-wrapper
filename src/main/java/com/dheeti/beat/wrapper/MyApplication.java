package com.dheeti.beat.wrapper;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import org.glassfish.jersey.media.multipart.MultiPartFeature;


/**
 * Created by jayramj on 11/6/14.
 */
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages(Patients.class.getPackage().getName());
        register(JspMvcFeature.class);
        register(MultiPartFeature.class);
        //register(MultiPartResource.class);
        register(LoggingFilter.class);
        register(FreemarkerMvcFeature.class);
    }

}
