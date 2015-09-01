package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.BusinessTrip;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessTripRepository extends AbstractRepo {

    public BusinessTrip findOne(Long id) {
        return (BusinessTrip) sessionFactory.openSession().get(BusinessTrip.class, id) ;
    }


    public BusinessTrip findOne(String name) {
        return (BusinessTrip)sessionFactory.openSession().createQuery("from business_trip p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<BusinessTrip> findAll() {
        return null;
    }


    public void create(BusinessTrip entity) {
        sessionFactory.openSession().save(entity);
    }


    public BusinessTrip update(BusinessTrip entity) {
        return null;
    }


    public void delete(BusinessTrip entity) {

    }


    public void deleteById(long entityId) {

    }
}