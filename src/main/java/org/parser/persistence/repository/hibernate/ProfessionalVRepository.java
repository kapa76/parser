package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.ProfessionalV;
import org.springframework.stereotype.Repository;

@Repository
public class ProfessionalVRepository extends AbstractRepo {

    public void create(ProfessionalV entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
//        Long id = (Long) session.createSQLQuery("select max(id) from professional_v").uniqueResult();
//        id = id + 1;
        session.save(entity);
        tx.commit();
        session.close();
    }

    public ProfessionalV update(ProfessionalV entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
        return entity;
    }

}