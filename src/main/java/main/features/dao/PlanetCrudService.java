package main.features.dao;

import main.features.entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.concurrent.ExecutionException;

interface PlanetCrud{
    public void save(Planet planet);
    public Planet findById(Long id);
    public void update(Planet planet);
    public void delete(Planet planet);
}
public class PlanetCrudService implements PlanetCrud {
    @Override
    public void save(Planet planet) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(planet);
            transaction.commit();
        } catch (Exception ex){
            System.err.println("Error occurred during saving planet " + ex.getMessage());
        }
    }

    @Override
    public Planet findById(Long id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return session.get(Planet.class, id);
        }catch (Exception ex){
            System.err.println("Error, planet not found. " + ex.getMessage());
            return null;
        }

    }

    @Override
    public void update(Planet planet) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(planet);
            transaction.commit();
        } catch (Exception ex) {
            System.err.println("Update failed " + ex.getMessage());
        }
    }

    @Override
    public void delete(Planet planet) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(planet);
            transaction.commit();
        } catch (Exception ex){
            System.err.println("Error occurred due to deleting " + ex.getMessage());
        }
    }
}
