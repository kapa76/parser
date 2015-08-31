package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionalDetailRepository extends HibernateRepositoryBase implements AbstractRepository<ProfessionalDetail> {
    @Override
    public ProfessionalDetail findOne(Long id) {
        return getHibernateTemplate().get(ProfessionalDetail.class, id);
    }

    @Override
    public ProfessionalDetail findOne(String name) {
        return null;
    }

    @Override
    public List<ProfessionalDetail> findAll() {
        return null;
    }

    @Override
    public void create(ProfessionalDetail entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public ProfessionalDetail update(ProfessionalDetail entity) {
        return null;
    }

    @Override
    public void delete(ProfessionalDetail entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}