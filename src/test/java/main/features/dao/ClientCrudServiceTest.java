package main.features.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.SneakyThrows;
import main.features.entity.Client;
import main.features.migration.Migration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTest {
    ClientCrudService clientCrudService = new ClientCrudService();

    @BeforeEach
    public void setup() throws SQLException {
        Migration.migrate();
    }

    @SneakyThrows
    @AfterEach
    public void afterEach() {
        EntityManager entityManager = HibernateUtils.getSessionFactory().createEntityManager();
        Session session = entityManager.unwrap(Session.class);

        // get the Hibernate session factory
        SessionFactory sessionFactory = session.getSessionFactory();

        // get the metadata for all the mapped entities
        Metadata metadata = sessionFactory.getMetadataBuilder().build();

        // iterate over all the entity names
        for (String entityName : metadata.getEntityNames()) {
            // create a query to truncate the table
            Query query = session.createStoredProcedureQuery("TRUNCATE TABLE " + entityName);
            query.executeUpdate();
        }
    }

    @SneakyThrows
    @Test
    public void createTest() {
        Client client = new Client("John Doe");

        clientCrudService.save(client);

        assertNotNull(client.getId());
    }

    @Test
    public void getByIdTest() {
        Client client = new Client("John Smith");
        clientCrudService.save(client);

        assertEquals(client.getId(), clientCrudService.findById(client.getId()).getId());
        assertEquals(client.getName(), clientCrudService.findById(client.getId()).getName());
    }

    @SneakyThrows
    @Test
    public void updateTest() {
        Client client = clientCrudService.findById(1L);
        client.setName("Elvis Smith");
        clientCrudService.update(client);

        assertEquals(client, clientCrudService.findById(1L));

    }

    @Test
    public void deleteTest() {
        List<Client> clients = clientCrudService.getAllClients();
        int clientsCount = clients.size();

        clientCrudService.delete(clientCrudService.findById(1L));

        List<Client> clients1 = clientCrudService.getAllClients();
        int clientsCount1 = clients1.size();

        assertEquals(clientsCount1, clientsCount);
    }
}
