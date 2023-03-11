package main.features.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import main.features.dao.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;

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
