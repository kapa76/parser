package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LangLevelResumeRepository extends AbstractRepo {

    public LangLevelResume findOne(Long id) {
        return (LangLevelResume) sessionFactory.openSession().get(LangLevelResume.class, id);
    }


    public LangLevelResume findOne(String name) {
        return null;
    }


    public List<LangLevelResume> findAll() {
        return null;
    }


    public void create(LangLevelResume entity) {
        sessionFactory.openSession().save(entity);
    }


    public LangLevelResume update(LangLevelResume entity) {
        return null;
    }


    public void delete(LangLevelResume entity) {

    }


    public void deleteById(long entityId) {

    }
}