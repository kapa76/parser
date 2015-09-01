package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.ProfessionalDetailV;
import org.springframework.stereotype.Repository;

@Repository
public class ProfessionalDetailVRepository extends AbstractRepo {

    public void create(ProfessionalDetailV entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    public ProfessionalDetailV update(ProfessionalDetailV entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
        return entity;
    }

}
