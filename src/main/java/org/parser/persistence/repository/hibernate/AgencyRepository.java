package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.Agency;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgencyRepository extends HibernateRepositoryBase implements AbstractRepository<Agency> {
    @Override
    public Agency findOne(Long id) {
        return getHibernateTemplate().get(Agency.class, id);
    }

    @Override
    public Agency findOne(String name) {
        return null;
    }

    @Override
    public List<Agency> findAll() {
        return null;
    }

    @Override
    public void create(Agency entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Agency update(Agency entity) {
        return null;
    }

    @Override
    public void delete(Agency entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}