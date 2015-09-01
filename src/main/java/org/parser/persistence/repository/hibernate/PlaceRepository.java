package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Place;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepository extends AbstractRepo {

    public Place findOne(Long id) {
        return (Place) sessionFactory.getCurrentSession().get(Place.class, id);
    }


    public Place findOne(String name) {
        return null;
    }


    public List<Place> findAll() {
        return null;
    }


    public void create(Place entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Place update(Place entity) {
        return null;
    }


    public void delete(Place entity) {

    }


    public void deleteById(long entityId) {

    }
}