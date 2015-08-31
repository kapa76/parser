package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LangLevelRepository extends HibernateRepositoryBase implements AbstractRepository<LangLevel> {
    @Override
    public LangLevel findOne(Long id) {
        return getHibernateTemplate().get(LangLevel.class, id);
    }

    @Override
    public LangLevel findOne(String name) {
        return null;
    }

    @Override
    public List<LangLevel> findAll() {
        return null;
    }

    @Override
    public void create(LangLevel entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public LangLevel update(LangLevel entity) {
        return null;
    }

    @Override
    public void delete(LangLevel entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}