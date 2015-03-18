package com.dheeti.beat.wrapper.persistence.DAO;

import com.dheeti.beat.wrapper.persistence.Configuration;
import com.dheeti.beat.wrapper.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by jayram on 18/3/15.
 */
public class ConfigurationDAO {
    public List<Configuration> getConfigurations() {
        Session session = HibernateUtil.getInstance().getSession();
        List<Configuration> configurationList = session.createCriteria(Configuration.class).list();
        return configurationList;
    }

    public boolean save(List<Configuration> updatedList) {
        boolean success = false;
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        for(Configuration conf : updatedList) {
            session.saveOrUpdate(conf);
        }
        session.flush();
        tx.commit();
        success = true;
        return success;
    }
}
