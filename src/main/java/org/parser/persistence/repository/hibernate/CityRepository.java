package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.City;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository extends AbstractRepo {

//    @Autowired
//    private SessionFactory sessionFactory;

    public City findOne(Long id) {

        return (City) sessionFactory.getCurrentSession().get(City.class, id);
    }


    public City findOne(String name) {
        return (City)sessionFactory.getCurrentSession().createQuery("from city p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<City> findAll() {
        return null;
    }


    public void create(City entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public City update(City entity) {
        return null;
    }


    public void delete(City entity) {

    }


    public void deleteById(long entityId) {

    }
}
