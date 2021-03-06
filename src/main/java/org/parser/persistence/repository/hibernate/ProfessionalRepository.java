package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Professional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionalRepository extends AbstractRepo {

    public Professional findOne(Long id) {
        return (Professional) sessionFactory.getCurrentSession().get(Professional.class, id);
    }


    public Professional findOne(String name) {
        return (Professional) sessionFactory.getCurrentSession().createQuery("from professional p where p.professionalName = :name").setParameter("name", name).uniqueResult();
    }


    public List<Professional> findAll() {
        return null;
    }


    public void create(Professional entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Professional update(Professional entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
        return entity;
    }


    public void delete(Professional entity) {

    }


    public void deleteById(long entityId) {

    }
}