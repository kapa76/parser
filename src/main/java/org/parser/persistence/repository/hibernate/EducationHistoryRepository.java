package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationHistoryRepository extends AbstractRepo {

    public EducationHistory findOne(Long id) {
        return (EducationHistory) sessionFactory.openSession().get(EducationHistory.class, id);
    }


    public EducationHistory findOne(String name) {
        return null;
    }


    public List<EducationHistory> findAll() {
        return null;
    }


    public void create(EducationHistory entity) {
        sessionFactory.openSession().save(entity);
    }


    public EducationHistory update(EducationHistory entity) {
        return null;
    }


    public void delete(EducationHistory entity) {

    }


    public void deleteById(long entityId) {

    }
}