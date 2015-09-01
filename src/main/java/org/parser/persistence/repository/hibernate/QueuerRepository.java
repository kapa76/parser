package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Queuer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueuerRepository extends AbstractRepo {

    public Queuer findOne(Long id) {
        return (Queuer) sessionFactory.getCurrentSession().get(Queuer.class, id);
    }


    public Queuer findOne(String name) {
        return null;
    }


    public List<Queuer> findAll() {
        return null;
    }


    public void create(Queuer entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Queuer update(Queuer entity) {
        return null;
    }


    public void delete(Queuer entity) {

    }


    public void deleteById(long entityId) {

    }
}