package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Agency;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgencyRepository extends AbstractRepo {

    public Agency findOne(Long id) {
        return (Agency) sessionFactory.getCurrentSession().get(Agency.class, id);
    }


    public Agency findOne(String name) {
        return (Agency) sessionFactory.getCurrentSession().createQuery("from agency p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Agency> findAll() {
        return null;
    }


    public void create(Agency entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Agency update(Agency entity) {
        return null;
    }


    public void delete(Agency entity) {

    }


    public void deleteById(long entityId) {

    }

    public Agency findOne(String name, long id) {
        return (Agency) sessionFactory.getCurrentSession().createQuery("from agency p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();
    }
}