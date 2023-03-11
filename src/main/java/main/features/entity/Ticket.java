package main.features.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import main.features.dao.ClientCrudService;
import main.features.dao.PlanetCrudService;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @Column(name = "client_id")
    private Client client;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    @Column(name = "from_planet_id")
    private Planet fromPlanet;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanet;

    public Ticket() {
    }

    public static boolean validate(Ticket ticket) {
        if (ticket == null || ticket.getClient() == null || ticket.getFromPlanet() == null
                || ticket.getToPlanet() == null) {
            return false;
        }

        Client client = new ClientCrudService().findById(ticket.getClient().getId());
        if (client == null) {
            return false;
        }

        Planet fromPlanet = new PlanetCrudService().findById(ticket.getFromPlanet().getId());
        if (fromPlanet == null) {
            return false;
        }

        Planet toPlanet = new PlanetCrudService().findById(ticket.getToPlanet().getId());
        if (toPlanet == null) {
            return false;
        }

        return true;
    }
}
