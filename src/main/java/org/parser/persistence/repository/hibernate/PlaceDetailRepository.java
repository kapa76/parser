package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceDetailRepository extends AbstractRepo {

    public PlaceDetail findOne(Long id) {
        return (PlaceDetail) sessionFactory.openSession().get(PlaceDetail.class, id);
    }


    public PlaceDetail findOne(String name) {
        return null;
    }


    public List<PlaceDetail> findAll() {
        return null;
    }


    public void create(PlaceDetail entity) {
        sessionFactory.openSession().save(entity);
    }


    public PlaceDetail update(PlaceDetail entity) {
        return null;
    }


    public void delete(PlaceDetail entity) {

    }


    public void deleteById(long entityId) {

    }
}