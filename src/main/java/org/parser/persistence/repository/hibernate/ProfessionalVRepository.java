package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Agency;
import org.parser.persistence.model.ProfessionalV;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionalVRepository extends AbstractRepo {

    public ProfessionalV findOne(Long id) {
        return (ProfessionalV) sessionFactory.getCurrentSession().get(ProfessionalV.class, id);
    }


    public ProfessionalV findOne(String name) {
        return null;
        //return (ProfessionalV) sessionFactory.getCurrentSession().createQuery("from professional_v p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Agency> findAll() {
        return null;
    }


    public void create(ProfessionalV entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public ProfessionalV update(ProfessionalV entity) {
        return null;
    }


    public void delete(ProfessionalV entity) {

    }


    public void deleteById(long entityId) {

    }

    public ProfessionalV findOne(String name, long id) {
        return (ProfessionalV) sessionFactory.getCurrentSession().createQuery("from agency p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();
    }
}