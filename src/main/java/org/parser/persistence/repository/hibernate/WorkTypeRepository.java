package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkTypeRepository extends AbstractRepo {

    public WorkType findOne(Long id) {
        return (WorkType) sessionFactory.openSession().get(WorkType.class, id);
    }


    public WorkType findOne(String name) {
        return null;
    }


    public List<WorkType> findAll() {
        return null;
    }


    public void create(WorkType entity) {
        sessionFactory.openSession().save(entity);
    }


    public WorkType update(WorkType entity) {
        return null;
    }


    public void delete(WorkType entity) {

    }


    public void deleteById(long entityId) {

    }
}