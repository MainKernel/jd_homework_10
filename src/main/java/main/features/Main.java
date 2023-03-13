package main.features;

import lombok.SneakyThrows;
import main.features.dao.ClientCrudService;
import main.features.entity.Client;
import main.features.migration.Migration;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Migration.migrate();
        Client client = new Client("John Doe");
        System.out.println("client.getId() = " + client.getId());
        ClientCrudService service = new ClientCrudService();
        service.save(client);
        System.out.println("client.getId() = " + client.getId());

    }
}
