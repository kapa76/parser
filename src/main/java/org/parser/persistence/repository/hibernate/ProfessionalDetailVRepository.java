package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Agency;
import org.parser.persistence.model.ProfessionalDetailV;
import org.parser.persistence.model.ProfessionalV;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionalDetailVRepository extends AbstractRepo  {
    public ProfessionalDetailV findOne(Long id) {
           return (ProfessionalDetailV) sessionFactory.getCurrentSession().get(ProfessionalDetailV.class, id);
       }


       public ProfessionalDetailV findOne(String name) {
           return null;
           //return (ProfessionalV) sessionFactory.getCurrentSession().createQuery("from professional_v p where p.name = :name").setParameter("name", name).uniqueResult();
       }


       public List<ProfessionalDetailV> findAll() {
           return null;
       }


       public void create(ProfessionalDetailV entity) {
           Session session = sessionFactory.openSession();
           Transaction tx = session.beginTransaction();
           session.save(entity);
           tx.commit();
           session.close();
       }


       public ProfessionalDetailV update(ProfessionalDetailV entity) {
           return null;
       }


       public void delete(ProfessionalDetailV entity) {

       }


       public void deleteById(long entityId) {

       }

       public ProfessionalDetailV findOne(String name, long id) {
           return null;
           //return (ProfessionalDetailV) sessionFactory.getCurrentSession().createQuery("from professional_detail_v p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();
       }
}
