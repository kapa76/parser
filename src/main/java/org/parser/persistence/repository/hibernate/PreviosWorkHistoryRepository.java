package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PreviosWorkHistoryRepository extends HibernateRepositoryBase implements AbstractRepository<PreviosWorkHistory> {
    @Override
    public PreviosWorkHistory findOne(Long id) {
        return getHibernateTemplate().get(PreviosWorkHistory.class, id);
    }

    @Override
    public PreviosWorkHistory findOne(String name) {
        return null;
    }

    @Override
    public List<PreviosWorkHistory> findAll() {
        return null;
    }

    @Override
    public void create(PreviosWorkHistory entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public PreviosWorkHistory update(PreviosWorkHistory entity) {
        return null;
    }

    @Override
    public void delete(PreviosWorkHistory entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}