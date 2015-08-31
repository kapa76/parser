package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacancyRepository extends HibernateRepositoryBase implements AbstractRepository<Vacancy> {
    @Override
    public Vacancy findOne(Long id) {
        return getHibernateTemplate().get(Vacancy.class, id);
    }

    @Override
    public Vacancy findOne(String name) {
        return null;
    }

    @Override
    public List<Vacancy> findAll() {
        return null;
    }

    @Override
    public void create(Vacancy entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Vacancy update(Vacancy entity) {
        return null;
    }

    @Override
    public void delete(Vacancy entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}