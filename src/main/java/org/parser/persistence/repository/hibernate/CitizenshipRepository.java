package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Citizenship;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CitizenshipRepository extends AbstractRepo {

    public Citizenship findOne(Long id) {
        return (Citizenship) sessionFactory.getCurrentSession().get(Citizenship.class, id);
    }


    public Citizenship findOne(String name) {
        return (Citizenship) sessionFactory.getCurrentSession().createQuery("from citizenship p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Citizenship> findAll() {
        return null;
    }


    public void create(Citizenship entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Citizenship update(Citizenship entity) {
        return null;
    }


    public void delete(Citizenship entity) {

    }


    public void deleteById(long entityId) {

    }

    public Citizenship findOne(String name, long id) {
        return (Citizenship) sessionFactory.getCurrentSession().createQuery("from citizenship p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}