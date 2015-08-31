package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChildrenResumeRepository extends HibernateRepositoryBase implements AbstractRepository<ChildrenResume> {
    @Override
    public ChildrenResume findOne(Long id) {
        return getHibernateTemplate().get(ChildrenResume.class, id);
    }

    @Override
    public ChildrenResume findOne(String name) {
        return null;
    }

    @Override
    public List<ChildrenResume> findAll() {
        return null;
    }

    @Override
    public void create(ChildrenResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public ChildrenResume update(ChildrenResume entity) {
        return null;
    }

    @Override
    public void delete(ChildrenResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}