package com.dheeti.beat.wrapper;

import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by jayram on 23/3/15.
 */
@Path("/")
public class Index {
    @Context
    private HttpServletRequest request;

    @GET
    @Path("/")
    @Produces("text/html")
    public Viewable getIndex(){
        return(new Viewable("/index.ftl"));
    }

}
