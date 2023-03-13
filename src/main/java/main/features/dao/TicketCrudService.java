package main.features.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import main.features.entity.Planet;
import main.features.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

interface TicketCrud {
    public void save(Planet planet);

    public Ticket findById(Long id);

    public void update(Planet planet);

    public void delete(Planet planet);
}

public class TicketCrudService implements TicketCrud {

    @Override
    public void save(Planet planet) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception exception) {
            System.err.println("Error occurred due to the saving " + exception.getMessage());
        }
    }

    @Override
    public Ticket findById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception ex) {
            System.err.println("Error, ticket not found");
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
            System.err.println("Error occurred due to the updating " + ex.getMessage());
        }
    }

    @Override
    public void delete(Planet planet) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        } catch (Exception ex) {
            System.err.println("Error occurred due to the deleting " + ex.getMessage());
        }
    }

    public List<Ticket> getAllTickets() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Ticket> criteriaQuery = builder.createQuery(Ticket.class);
            Root<Ticket> root = criteriaQuery.from(Ticket.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("There is no Tickets");
            return null;
        }
    }
}
