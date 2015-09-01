package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.PlaceDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceDetailRepository extends AbstractRepo {

    public PlaceDetail findOne(Long id) {
        return (PlaceDetail) sessionFactory.getCurrentSession().get(PlaceDetail.class, id);
    }


    public PlaceDetail findOne(String name) {
        return null;
    }


    public List<PlaceDetail> findAll() {
        return null;
    }


    public void create(PlaceDetail entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public PlaceDetail update(PlaceDetail entity) {
        return null;
    }


    public void delete(PlaceDetail entity) {

    }


    public void deleteById(long entityId) {

    }
}