package main.features.dao;

import lombok.SneakyThrows;
import main.features.entity.Client;
import main.features.migration.Migration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTest {
    ClientCrudService clientCrudService = new ClientCrudService();

    @BeforeEach
    public void setup() {
        Migration.migrate();
    }

    @SneakyThrows
    @AfterEach
    public void afterEach() {
        Connection connection = DriverManager.getConnection("jdbc:h2:./test", "sa", "");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP ALL OBJECTS DELETE FILES");
        connection.close();
    }

    @SneakyThrows
    @Test
    public void testThatSaveWorks() {
        Client client = new Client("John Doe");

        clientCrudService.save(client);

        assertNotNull(client.getId());
    }

    @Test
    public void testThatGetByIdWorks() {
        Client client = new Client("John Smith");
        clientCrudService.save(client);

        assertEquals(client.getId(), clientCrudService.findById(client.getId()).getId());
        assertEquals(client.getName(), clientCrudService.findById(client.getId()).getName());
    }

    @SneakyThrows
    @Test
    public void testThatUpdateWorks() {
        Client client = clientCrudService.findById(1L);
        client.setName("Elvis Smith");
        clientCrudService.update(client);

        assertEquals(client, clientCrudService.findById(1L));

    }

    @Test
    public void testThatDeleteWorks() {
        List<Client> clients = clientCrudService.getAllClients();
        int clientsCount = clients.size();

        clientCrudService.delete(clientCrudService.findById(1L));

        List<Client> clients1 = clientCrudService.getAllClients();
        int clientsCount1 = clients1.size();

        assertEquals(clientsCount1, clientsCount-1);
    }
}
