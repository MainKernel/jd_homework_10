package main.features.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import main.features.entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

interface PlanetCrud {
    public void save(Planet planet);

    public Planet findById(String id);

    public void update(Planet planet);

    public void delete(Planet planet);
}

public class PlanetCrudService implements PlanetCrud{
    @Override
    public void save(Planet planet) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception ex) {
            System.err.println("Error occurred during saving planet " + ex.getMessage());
        }
    }

    @Override
    public Planet findById(String id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Planet.class, id);
        } catch (Exception ex) {
            System.err.println("Error, planet not found. " + ex.getMessage());
            return null;
        }

    }

    @Override
    public void update(Planet planet) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
        } catch (Exception ex) {
            System.err.println("Update failed " + ex.getMessage());
        }
    }

    @Override
    public void delete(Planet planet) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        } catch (Exception ex) {
            System.err.println("Error occurred due to deleting " + ex.getMessage());
        }
    }

    public List<Planet> getAllPlanets() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Planet> criteriaQuery = builder.createQuery(Planet.class);
            Root<Planet> root = criteriaQuery.from(Planet.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("There is no Planets");
            return null;
        }
    }
}
