package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationTypeResumeRepository extends AbstractRepo {

    public EducationTypeResume findOne(Long id) {
        return (EducationTypeResume) sessionFactory.openSession().get(EducationTypeResume.class, id);
    }


    public EducationTypeResume findOne(String name) {
        return null;
    }


    public List<EducationTypeResume> findAll() {
        return null;
    }


    public void create(EducationTypeResume entity) {
        sessionFactory.openSession().save(entity);
    }


    public EducationTypeResume update(EducationTypeResume entity) {
        return null;
    }


    public void delete(EducationTypeResume entity) {

    }

    public void deleteById(long entityId) {

    }
}