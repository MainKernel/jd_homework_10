package main.features.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

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

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
