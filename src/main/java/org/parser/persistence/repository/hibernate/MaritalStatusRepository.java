package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusRepository extends AbstractRepo {

    public MaritalStatus findOne(Long id) {
        return (MaritalStatus) sessionFactory.openSession().get(MaritalStatus.class, id);
    }


    public MaritalStatus findOne(String name) {
        return null;
    }


    public List<MaritalStatus> findAll() {
        return null;
    }


    public void create(MaritalStatus entity) {
        sessionFactory.openSession().save(entity);
    }


    public MaritalStatus update(MaritalStatus entity) {
        return null;
    }


    public void delete(MaritalStatus entity) {

    }


    public void deleteById(long entityId) {

    }
}