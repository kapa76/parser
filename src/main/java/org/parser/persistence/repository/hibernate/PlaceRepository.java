package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepository extends AbstractRepo {

    public Place findOne(Long id) {
        return (Place) sessionFactory.openSession().get(Place.class, id);
    }


    public Place findOne(String name) {
        return null;
    }


    public List<Place> findAll() {
        return null;
    }


    public void create(Place entity) {
        sessionFactory.openSession().save(entity);
    }


    public Place update(Place entity) {
        return null;
    }


    public void delete(Place entity) {

    }


    public void deleteById(long entityId) {

    }
}