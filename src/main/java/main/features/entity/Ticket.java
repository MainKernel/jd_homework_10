package main.features.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    Long id;

    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "client_id")
    Integer clientId;
    @Column(name = "from_planet_id")
    String fromPlanet;
    @Column(name = "to_planet_id")
    String toPlanet;

    public Ticket(){
    }

}
