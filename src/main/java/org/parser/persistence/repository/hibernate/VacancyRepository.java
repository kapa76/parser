package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacancyRepository extends AbstractRepo {

    public Vacancy findOne(Long id) {
        return (Vacancy) sessionFactory.openSession().get(Vacancy.class, id);
    }


    public Vacancy findOne(String name) {
        return null;
    }


    public List<Vacancy> findAll() {
        return null;
    }


    public void create(Vacancy entity) {
        sessionFactory.openSession().save(entity);
    }


    public Vacancy update(Vacancy entity) {
        return null;
    }


    public void delete(Vacancy entity) {

    }


    public void deleteById(long entityId) {

    }
}