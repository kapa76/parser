package org.parser.persistence.repository.hibernate;


import org.hibernate.SessionFactory;
import org.parser.persistence.model.City;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CityRepository extends AbstractRepo {

//    @Autowired
//    private SessionFactory sessionFactory;

    public City findOne(Long id) {

        return (City) sessionFactory.openSession().get(City.class, id);
    }


    public City findOne(String name) {
        return null;
    }


    public List<City> findAll() {
        return null;
    }


    public void create(City entity) {
        sessionFactory.openSession().save(entity);
    }


    public City update(City entity) {
        return null;
    }


    public void delete(City entity) {

    }


    public void deleteById(long entityId) {

    }
}
