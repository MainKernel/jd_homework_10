package main.features.dao;

import lombok.SneakyThrows;
import main.features.entity.Planet;
import main.features.migration.Migration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCrudTest {
    PlanetCrudService planetCrudService = new PlanetCrudService();

    @BeforeEach
    public void setup() {
        Migration.migrate();
    }

    @SneakyThrows
    @AfterEach
    public void afterEach(){
        Connection connection = DriverManager.getConnection("jdbc:h2:./test", "sa", "");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP ALL OBJECTS DELETE FILES");
        connection.close();
    }


    @SneakyThrows
    @Test
    void testThatSaveWorks() {
        Planet planet = new Planet("MARS2", "mars2");
        planetCrudService.save(planet);

        assertEquals(planet, planetCrudService.findById(planet.getId()));
    }

    @Test
    void testThatFindByIdWorks() {
        Planet planet = planetCrudService.findById("MARS");

        assert("mars".equalsIgnoreCase(planet.getName()));
    }

    @Test
    void TestThatUpdateWorks() {
        Planet planet = planetCrudService.findById("URAN");
        planet.setName("Uran");
        planetCrudService.update(planet);

        assert ("Uran".equalsIgnoreCase(planetCrudService.findById("URAN").getName()));

    }

    @Test
    void testThatDeleteWorks() {
        List<Planet> clients = planetCrudService.getAllPlanets();
        int clientsCount = clients.size();

        planetCrudService.delete(planetCrudService.findById("MARS"));

        List<Planet> clients1 = planetCrudService.getAllPlanets();
        int clientsCount1 = clients1.size();

        assertEquals(clientsCount1, clientsCount-1);
    }
}