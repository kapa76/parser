package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusResumeRepository extends AbstractRepo {

    public MaritalStatusResume findOne(Long id) {
        return (MaritalStatusResume) sessionFactory.openSession().get(MaritalStatusResume.class, id);
    }


    public MaritalStatusResume findOne(String name) {
        return null;
    }


    public List<MaritalStatusResume> findAll() {
        return null;
    }


    public void create(MaritalStatusResume entity) {
        sessionFactory.openSession().save(entity);
    }


    public MaritalStatusResume update(MaritalStatusResume entity) {
        return null;
    }


    public void delete(MaritalStatusResume entity) {

    }


    public void deleteById(long entityId) {

    }
}