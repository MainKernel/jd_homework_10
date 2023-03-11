package main.features.dao;

import lombok.SneakyThrows;
import main.features.entity.Client;
import main.features.entity.Planet;
import main.features.migration.Migration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCrudTest {
    PlanetCrudService crudService = new PlanetCrudService();

    @BeforeEach
    public void setup() throws SQLException {
        Migration.migrate();
    }

    @AfterEach
    public void afterEach(){
        Planet planet = crudService.findById("URAN");
        planet.setName("Uranus");
        crudService.update(planet);
    }


    @SneakyThrows
    @Test
    void save() {
        Planet planet = new Planet("MARS2", "mars2");
        crudService.save(planet);
        List<Planet> planets = crudService.getAllPlanets();

        assertEquals("mars2", crudService.findById("MARS2").getName());
    }

    @Test
    void findById() {
        Planet planet = crudService.findById("MARS");

        assert("mars".equalsIgnoreCase(planet.getName()));
    }

    @Test
    void update() {
        Planet planet = crudService.findById("URAN");
        planet.setName("Uran");
        crudService.update(planet);

        assert ("Uran".equalsIgnoreCase(crudService.findById("URAN").getName()));

    }

    @Test
    void delete() {
        List<Planet> planets = crudService.getAllPlanets();
        String maxId = planets.get(planets.size()-1).getId();

        Planet planet = crudService.findById(maxId);
        crudService.delete(planet);


        Planet deletedClient = crudService.findById(maxId);
        assertNull(deletedClient);
    }
}