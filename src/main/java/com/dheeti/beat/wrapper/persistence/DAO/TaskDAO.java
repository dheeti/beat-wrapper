package com.dheeti.beat.wrapper.persistence.DAO;

import com.dheeti.beat.wrapper.persistence.Configuration;
import com.dheeti.beat.wrapper.persistence.HibernateUtil;
import com.dheeti.beat.wrapper.persistence.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by jayram on 19/3/15.
 */
public class TaskDAO {
    public List<Task> getTaskConfigurations() {
        Session session = HibernateUtil.getInstance().getSession();
        List<Task> taskList = session.createCriteria(Task.class).list();
        return taskList;
    }

    public boolean save(List<Task> updatedList) {
        boolean success = false;
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        for(Task task : updatedList) {
            session.saveOrUpdate(task);
        }
        session.flush();
        tx.commit();
        success = true;
        return success;
    }
}
