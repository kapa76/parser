package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenderRepository extends AbstractRepo {

    public Gender findOne(Long id) {
        return (Gender) sessionFactory.getCurrentSession().get(Gender.class, id);
    }


    public Gender findOne(String name) {
        return null;
    }


    public List<Gender> findAll() {
        return null;
    }


    public void create(Gender entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    public Gender update(Gender entity) {
        return null;
    }


    public void delete(Gender entity) {

    }


    public void deleteById(long entityId) {

    }

    public Gender findOne(String name, long id) {
        return (Gender) sessionFactory.getCurrentSession().createQuery("from gender p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}