package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.Agency;
import org.parser.persistence.model.BusinessTrip;
import org.parser.persistence.model.Children;
import org.parser.persistence.model.City;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChildrenRepository extends HibernateRepositoryBase implements AbstractRepository<Children> {
    @Override
    public Children findOne(Long id) {
        return getHibernateTemplate().get(Children.class, id);
    }

    @Override
    public Children findOne(String name) {
        return null;
    }

    @Override
    public List<Children> findAll() {
        return null;
    }

    @Override
    public void create(Children entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Children update(Children entity) {
        return null;
    }

    @Override
    public void delete(Children entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}