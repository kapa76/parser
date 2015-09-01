package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationRepository extends AbstractRepo {

    public Education findOne(Long id) {
        return (Education) sessionFactory.openSession().get(Education.class, id);
    }


    public Education findOne(String name) {
        return null;
    }


    public List<Education> findAll() {
        return null;
    }


    public void create(Education entity) {
        sessionFactory.openSession().save(entity);
    }


    public Education update(Education entity) {
        return null;
    }


    public void delete(Education entity) {

    }


    public void deleteById(long entityId) {

    }
}