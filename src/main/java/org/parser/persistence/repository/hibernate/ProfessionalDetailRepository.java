package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionalDetailRepository extends AbstractRepo {

    public ProfessionalDetail findOne(Long id) {
        return (ProfessionalDetail) sessionFactory.getCurrentSession().get(ProfessionalDetail.class, id);
    }


    public ProfessionalDetail findOne(String name) {
        return null;
    }


    public List<ProfessionalDetail> findAll() {
        return null;
    }


    public void create(ProfessionalDetail entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public ProfessionalDetail update(ProfessionalDetail entity) {
        return null;
    }


    public void delete(ProfessionalDetail entity) {

    }


    public void deleteById(long entityId) {

    }
}