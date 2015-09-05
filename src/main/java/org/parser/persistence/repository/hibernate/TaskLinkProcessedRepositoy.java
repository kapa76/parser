package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.TaskLink;
import org.parser.persistence.model.TypeOfWork;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskLinkProcessedRepositoy extends AbstractRepo {

    public TaskLink findOne(Long id) {
        return (TaskLink) sessionFactory.getCurrentSession().get(TaskLink.class, id);
    }


    public void create(TaskLink entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public TaskLink update(TaskLink entity) {
        return null;
    }

    public List<TaskLink> getNotProcessedLink(int i) {
        return null;
    }
}