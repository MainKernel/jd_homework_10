package main.features.dao;

import main.features.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

interface ClientCrud{
    public void save(Client client);
    public Client findById(Long id);
    public void update(Client client);
    public void delete(Client client);
}

public class ClientCrudService implements ClientCrud{

    @Override
    public void save(Client client) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Client findById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Client.class, id);
        }catch (Exception ex){
            System.err.println("User with id: " + id + " not found");
            return null;
        }
    }

    @Override
    public void update(Client client) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (Exception ex){
            System.err.println("Error updating user: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Client client) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }catch (Exception ex){
            System.err.println("Error deleting user: " + ex.getMessage());
        }
    }
}
