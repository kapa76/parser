package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusResumeGenderRepository extends AbstractRepo {

    public MaritalStatusResumeGender findOne(Long id) {
        return (MaritalStatusResumeGender) sessionFactory.openSession().get(MaritalStatusResumeGender.class, id);
    }


    public MaritalStatusResumeGender findOne(String name) {
        return null;
    }


    public List<MaritalStatusResumeGender> findAll() {
        return null;
    }


    public void create(MaritalStatusResumeGender entity) {
        sessionFactory.openSession().save(entity);
    }


    public MaritalStatusResumeGender update(MaritalStatusResumeGender entity) {
        return null;
    }


    public void delete(MaritalStatusResumeGender entity) {

    }


    public void deleteById(long entityId) {

    }
}