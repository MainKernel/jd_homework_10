package main.features.dao;

import lombok.SneakyThrows;
import main.features.entity.Client;
import main.features.migration.Migration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTest {

    @BeforeEach
    public void setup() throws SQLException {
        Migration.migrate();
    }

    @SneakyThrows
    @Test
    public void TestCreate(){
        Client client = new Client("John Doe");
        ClientCrudService clientCrudService = new ClientCrudService();
        clientCrudService.save(client);


        assertEquals(client.getId(), clientCrudService.findById(client.getId()).getId());
    }
}