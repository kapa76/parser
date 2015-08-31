package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkTypeRepository extends HibernateRepositoryBase implements AbstractRepository<WorkType> {
    @Override
    public WorkType findOne(Long id) {
        return getHibernateTemplate().get(WorkType.class, id);
    }

    @Override
    public WorkType findOne(String name) {
        return null;
    }

    @Override
    public List<WorkType> findAll() {
        return null;
    }

    @Override
    public void create(WorkType entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public WorkType update(WorkType entity) {
        return null;
    }

    @Override
    public void delete(WorkType entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}