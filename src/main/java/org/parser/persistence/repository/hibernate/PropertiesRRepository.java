package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertiesRRepository extends AbstractRepo {

    public PropertiesR findOne(Long id) {
        return
                (PropertiesR) sessionFactory.getCurrentSession().get(PropertiesR.class, id);
    }


    public PropertiesR findOne(String name) {
        return null;
    }


    public List<PropertiesR> findAll() {
        return null;
    }


    public void create(PropertiesR entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public PropertiesR update(PropertiesR entity) {
        return null;
    }

    public void delete(PropertiesR entity) {

    }


    public void deleteById(long entityId) {

    }
}