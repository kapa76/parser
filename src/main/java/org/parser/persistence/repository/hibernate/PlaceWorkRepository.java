package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceWorkRepository extends AbstractRepo {

    public PlaceWork findOne(Long id) {
        return (PlaceWork) sessionFactory.openSession().get(PlaceWork.class, id);
    }


    public PlaceWork findOne(String name) {
        return (PlaceWork)sessionFactory.openSession().createQuery("from place_work p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<PlaceWork> findAll() {
        return null;
    }


    public void create(PlaceWork entity) {
        sessionFactory.openSession().save(entity);
    }


    public PlaceWork update(PlaceWork entity) {
        return null;
    }


    public void delete(PlaceWork entity) {

    }


    public void deleteById(long entityId) {

    }
}