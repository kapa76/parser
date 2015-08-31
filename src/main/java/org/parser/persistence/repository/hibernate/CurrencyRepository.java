package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyRepository extends HibernateRepositoryBase implements AbstractRepository<Currency> {
    @Override
    public Currency findOne(Long id) {
        return getHibernateTemplate().get(Currency.class, id);
    }

    @Override
    public Currency findOne(String name) {
        return null;
    }

    @Override
    public List<Currency> findAll() {
        return null;
    }

    @Override
    public void create(Currency entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Currency update(Currency entity) {
        return null;
    }

    @Override
    public void delete(Currency entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}