package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Agency;
import org.parser.persistence.model.History;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryRepository extends AbstractRepo {

    public void create(History entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

}