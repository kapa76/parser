package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.HistoryLoad;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryLoadRepository extends AbstractRepo {

    public void create(HistoryLoad entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

}