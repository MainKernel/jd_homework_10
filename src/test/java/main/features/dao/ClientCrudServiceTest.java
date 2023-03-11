package main.features.dao;

import lombok.SneakyThrows;
import main.features.entity.Client;
import main.features.migration.Migration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.SQLException;
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
    public void afterEach(){
        Client client = clientCrudService.findById(1L);
        client.setName("John Smith");
        clientCrudService.update(client);
    }

    @SneakyThrows
    @Test
    public void TestCreate() {
        Client client = new Client("John Doe");

        clientCrudService.save(client);

        assertNotNull(client.getId());
    }

    @Test
    public void TestGetById() {
        Client client = clientCrudService.findById(1L);

        assertEquals("John Smith", client.getName());
    }

    @SneakyThrows
    @Test
    public void TestUpdate(){
        Client client = clientCrudService.findById(1L);
        client.setName("Elvis Smith");
        clientCrudService.update(client);

        assertEquals("Elvis Smith", clientCrudService.findById(1L).getName());

    }

    @Test
    public void TestDelete() {
        List<Client> clients = clientCrudService.getAllClients();
        Long maxId = clients.get(clients.size()-1).getId();

        Client client = clientCrudService.findById(maxId);
        clientCrudService.delete(client);


        Client deletedClient = clientCrudService.findById(maxId);
        assertNull(deletedClient);
    }
}
