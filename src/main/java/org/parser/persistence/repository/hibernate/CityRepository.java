package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.City;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component(value="city")
@Repository
public class CityRepository extends HibernateRepositoryBase implements AbstractRepository<City> {
    @Override
    public City findOne(Long id) {
        return getHibernateTemplate().get(City.class, id);
    }

    @Override
    public City findOne(String name) {
        return null;
    }

    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public void create(City entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public City update(City entity) {
        return null;
    }

    @Override
    public void delete(City entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
