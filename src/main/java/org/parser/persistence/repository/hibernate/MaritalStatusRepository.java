package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusRepository extends HibernateRepositoryBase implements AbstractRepository<MaritalStatus> {
    @Override
    public MaritalStatus findOne(Long id) {
        return getHibernateTemplate().get(MaritalStatus.class, id);
    }

    @Override
    public MaritalStatus findOne(String name) {
        return null;
    }

    @Override
    public List<MaritalStatus> findAll() {
        return null;
    }

    @Override
    public void create(MaritalStatus entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public MaritalStatus update(MaritalStatus entity) {
        return null;
    }

    @Override
    public void delete(MaritalStatus entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}