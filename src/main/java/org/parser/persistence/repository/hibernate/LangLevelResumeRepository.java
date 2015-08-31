package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LangLevelResumeRepository extends HibernateRepositoryBase implements AbstractRepository<LangLevelResume> {
    @Override
    public LangLevelResume findOne(Long id) {
        return getHibernateTemplate().get(LangLevelResume.class, id);
    }

    @Override
    public LangLevelResume findOne(String name) {
        return null;
    }

    @Override
    public List<LangLevelResume> findAll() {
        return null;
    }

    @Override
    public void create(LangLevelResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public LangLevelResume update(LangLevelResume entity) {
        return null;
    }

    @Override
    public void delete(LangLevelResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}