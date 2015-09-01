package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienceRepository extends AbstractRepo {

    public Experience findOne(Long id) {
        return (Experience) sessionFactory.openSession().get(Experience.class, id);
    }


    public Experience findOne(String name) {
        return null;
    }


    public List<Experience> findAll() {
        return null;
    }


    public void create(Experience entity) {
        sessionFactory.openSession().save(entity);
    }


    public Experience update(Experience entity) {
        return null;
    }


    public void delete(Experience entity) {

    }


    public void deleteById(long entityId) {

    }
}