package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.Agency;
import org.parser.persistence.model.BusinessTrip;
import org.parser.persistence.model.City;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessTripRepository extends HibernateRepositoryBase implements AbstractRepository<BusinessTrip> {
    @Override
    public BusinessTrip findOne(Long id) {
        return getHibernateTemplate().get(BusinessTrip.class, id);
    }

    @Override
    public BusinessTrip findOne(String name) {
        return null;
    }

    @Override
    public List<BusinessTrip> findAll() {
        return null;
    }

    @Override
    public void create(BusinessTrip entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public BusinessTrip update(BusinessTrip entity) {
        return null;
    }

    @Override
    public void delete(BusinessTrip entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}