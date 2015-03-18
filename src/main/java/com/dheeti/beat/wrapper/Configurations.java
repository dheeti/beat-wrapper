package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.helper.ReloadServletContextHelper;
import com.dheeti.beat.wrapper.persistence.Configuration;
import com.dheeti.beat.wrapper.persistence.DAO.ConfigurationDAO;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jayram on 18/3/15.
 */
@Path("configurations")
public class Configurations {
    @Context
    private HttpServletRequest request;

    @GET
    @Path("/system")
    @Produces("text/html")
    public Viewable getConfigurations() {
        List<Configuration> model = new ArrayList<Configuration>();
        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        List<Configuration> configurationList = configurationDAO.getConfigurations();
        for(Configuration conf : configurationList){
            model.add(conf);
        }
        return new Viewable("/systemconfig.ftl",model);
    }

    @POST
    @Path("/system")
    @Produces("text/html")
    public String saveConfigurations(@FormParam("id") List<String> idList,@FormParam("configkey")List<String> configKeyList,
                                     @FormParam("configvalue")List<String> configValueList) {

        List<Configuration> updatedList = new ArrayList<Configuration>();
        configKeyList.get(0);
        int index = 0;
        for(String id : idList){
            Configuration conf = new Configuration(new Integer(id),configKeyList.get(index),configValueList.get(index));
            updatedList.add(conf);
            index++;
        }
        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        boolean result = configurationDAO.save(updatedList);
        if(result)
            ReloadServletContextHelper.reload(updatedList,request.getSession().getServletContext());
        String message = "Configuration Changes Saved Successfully";
        return message;
    }


}
