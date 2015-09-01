package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertiesVRepository extends AbstractRepo {

    public PropertiesV findOne(Long id) {
        return (PropertiesV) sessionFactory.openSession().get(PropertiesV.class, id);
    }


    public PropertiesV findOne(String name) {
        return null;
    }


    public List<PropertiesV> findAll() {
        return null;
    }


    public void create(PropertiesV entity) {
        sessionFactory.openSession().save(entity);
    }


    public PropertiesV update(PropertiesV entity) {
        return null;
    }


    public void delete(PropertiesV entity) {

    }


    public void deleteById(long entityId) {

    }
}