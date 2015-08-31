package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertiesVRepository extends HibernateRepositoryBase implements AbstractRepository<PropertiesV> {
    @Override
    public PropertiesV findOne(Long id) {
        return getHibernateTemplate().get(PropertiesV.class, id);
    }

    @Override
    public PropertiesV findOne(String name) {
        return null;
    }

    @Override
    public List<PropertiesV> findAll() {
        return null;
    }

    @Override
    public void create(PropertiesV entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public PropertiesV update(PropertiesV entity) {
        return null;
    }

    @Override
    public void delete(PropertiesV entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}