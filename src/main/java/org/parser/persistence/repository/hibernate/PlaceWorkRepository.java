package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.PlaceWork;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceWorkRepository extends AbstractRepo {

    public PlaceWork findOne(Long id) {
        return (PlaceWork) sessionFactory.getCurrentSession().get(PlaceWork.class, id);
    }


    public PlaceWork findOne(String name) {
        return (PlaceWork) sessionFactory.getCurrentSession().createQuery("from place_work p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<PlaceWork> findAll() {
        return null;
    }


    public void create(PlaceWork entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public PlaceWork update(PlaceWork entity) {
        return null;
    }


    public void delete(PlaceWork entity) {

    }


    public void deleteById(long entityId) {

    }

    public PlaceWork findOne(String name, long id) {
        return (PlaceWork) sessionFactory.getCurrentSession().createQuery("from place_work p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}