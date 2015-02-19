package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.common.StringConstants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.HashMap;

/**
 * Created by jayram on 19/2/15.
 */
@Path("patients")
public class Users implements StringConstants{
    @Context
    private HttpServletRequest request;
    private HashMap<String,Object> model = new HashMap<>();

    public HashMap<String,Object> getModel() {
        return model;
    }

    public void setModel(HashMap<String,Object> model) {
        this.model = model;
    }

    @POST
    @Produces("text/html")
    public void registerUser(){

    }
}
