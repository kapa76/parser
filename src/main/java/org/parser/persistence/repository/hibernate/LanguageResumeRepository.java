package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageResumeRepository extends AbstractRepo {

    public LanguageResume findOne(Long id) {
        return (LanguageResume) sessionFactory.openSession().get(LanguageResume.class, id);
    }


    public LanguageResume findOne(String name) {
        return null;
    }


    public List<LanguageResume> findAll() {
        return null;
    }


    public void create(LanguageResume entity) {
        sessionFactory.openSession().save(entity);
    }


    public LanguageResume update(LanguageResume entity) {
        return null;
    }


    public void delete(LanguageResume entity) {

    }


    public void deleteById(long entityId) {

    }
}