package main.features.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "planet")
@Getter
@Setter
public class Planet {
    // Planet id in range between  A-Z and 0-9
    // Planet name in range between 1-500


    @Id
    @Column(name = "id")
    @Pattern(regexp = "^[A-Z0-9]+$")
    private String id;

    @Column(name = "name", columnDefinition = "VARCHAR(500) NOT NULL CHECK(LENGTH(name) >= 1 AND LENGTH(name) <= 500 ")
    private String name;

    public Planet() {

    }

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planet planet = (Planet) o;

        if (!Objects.equals(id, planet.id)) return false;
        return Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
