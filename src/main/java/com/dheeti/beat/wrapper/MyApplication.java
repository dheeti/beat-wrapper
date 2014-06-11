package com.dheeti.beat.wrapper;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

/**
 * Created by jayramj on 11/6/14.
 */
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages(Patients.class.getPackage().getName());
        register(JspMvcFeature.class);
    }
}
