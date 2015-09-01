package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertiesRRepository extends AbstractRepo {

    public PropertiesR findOne(Long id) {
        return
                (PropertiesR) sessionFactory.openSession().get(PropertiesR.class, id);
    }


    public PropertiesR findOne(String name) {
        return null;
    }


    public List<PropertiesR> findAll() {
        return null;
    }


    public void create(PropertiesR entity) {
        sessionFactory.openSession().save(entity);
    }


    public PropertiesR update(PropertiesR entity) {
        return null;
    }

    public void delete(PropertiesR entity) {

    }


    public void deleteById(long entityId) {

    }
}