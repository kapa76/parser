package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageRepository extends HibernateRepositoryBase implements AbstractRepository<Language> {
    @Override
    public Language findOne(Long id) {
        return getHibernateTemplate().get(Language.class, id);
    }

    @Override
    public Language findOne(String name) {
        return null;
    }

    @Override
    public List<Language> findAll() {
        return null;
    }

    @Override
    public void create(Language entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Language update(Language entity) {
        return null;
    }

    @Override
    public void delete(Language entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}