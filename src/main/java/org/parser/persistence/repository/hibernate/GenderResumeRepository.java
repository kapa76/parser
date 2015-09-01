package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenderResumeRepository extends AbstractRepo {

    public GenderResume findOne(Long id) {
        return  (GenderResume) sessionFactory.openSession().get(GenderResume.class, id);
    }


    public GenderResume findOne(String name) {
        return null;
    }


    public List<GenderResume> findAll() {
        return null;
    }


    public void create(GenderResume entity) {
        sessionFactory.openSession().save(entity);
    }


    public GenderResume update(GenderResume entity) {
        return null;
    }


    public void delete(GenderResume entity) {

    }


    public void deleteById(long entityId) {

    }
}