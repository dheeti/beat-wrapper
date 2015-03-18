package com.dheeti.beat.wrapper.helper;

import com.dheeti.beat.wrapper.persistence.Configuration;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by jayram on 18/3/15.
 */
public class ReloadServletContextHelper {
    public static void reload(List<Configuration> configurationList,ServletContext sc){
        for(Configuration conf : configurationList){
            sc.setAttribute(conf.getKey(),conf.getValue());
        }
    }
}
