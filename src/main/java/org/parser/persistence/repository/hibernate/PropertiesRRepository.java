package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertiesRRepository extends HibernateRepositoryBase implements AbstractRepository<PropertiesR> {
    @Override
    public PropertiesR findOne(Long id) {
        return getHibernateTemplate().get(PropertiesR.class, id);
    }

    @Override
    public PropertiesR findOne(String name) {
        return null;
    }

    @Override
    public List<PropertiesR> findAll() {
        return null;
    }

    @Override
    public void create(PropertiesR entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public PropertiesR update(PropertiesR entity) {
        return null;
    }

    @Override
    public void delete(PropertiesR entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}