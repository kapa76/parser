package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PreviosWorkHistoryRepository extends AbstractRepo {

    public PreviosWorkHistory findOne(Long id) {
        return (PreviosWorkHistory) sessionFactory.openSession().get(PreviosWorkHistory.class, id);
    }


    public PreviosWorkHistory findOne(String name) {
        return null;
    }


    public List<PreviosWorkHistory> findAll() {
        return null;
    }


    public void create(PreviosWorkHistory entity) {
        sessionFactory.openSession().save(entity);
    }


    public PreviosWorkHistory update(PreviosWorkHistory entity) {
        return null;
    }


    public void delete(PreviosWorkHistory entity) {

    }


    public void deleteById(long entityId) {

    }
}