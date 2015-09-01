package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionalDetailRepository extends AbstractRepo {

    public ProfessionalDetail findOne(Long id) {
        return (ProfessionalDetail) sessionFactory.openSession().get(ProfessionalDetail.class, id);
    }


    public ProfessionalDetail findOne(String name) {
        return null;
    }


    public List<ProfessionalDetail> findAll() {
        return null;
    }


    public void create(ProfessionalDetail entity) {
        sessionFactory.openSession().save(entity);
    }


    public ProfessionalDetail update(ProfessionalDetail entity) {
        return null;
    }


    public void delete(ProfessionalDetail entity) {

    }


    public void deleteById(long entityId) {

    }
}