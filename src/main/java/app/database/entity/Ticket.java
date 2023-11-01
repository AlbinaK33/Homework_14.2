package app.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {

    private static final Logger log = Logger.getLogger(Ticket.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanet;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanet;



    @PrePersist
    public void logNewTicketAttempt() {
        log.info("Attempting to add new ticket: " + client + " : " + fromPlanet + " : " + toPlanet);
    }

    @PostPersist
    public void logNewTicketAdded() {
        log.info("Added ticket '" + client + " : " + fromPlanet + " : " + toPlanet + "' with ID: " + id);
    }

    @PreUpdate
    public void logTicketUpdateAttempt() {
        log.info("Attempting to update ticket: " + client + " : " + fromPlanet + " : " + toPlanet);
    }

    @PostUpdate
    public void logTicketUpdate() {
        log.info("Updated ticket: " + client + " : " + fromPlanet + " : " + toPlanet);
    }

    @PostLoad
    public void logTicketLoad() {
        log.info("Load ticket: " + client + " : " + fromPlanet + " : " + toPlanet);
    }

    @PreRemove
    public void logTicketRemovalAttempt() {
        log.info("Attempting to delete ticket: " + client + " : " + fromPlanet + " : " + toPlanet);
    }

    @PostRemove
    public void logTicketRemoval() {
        log.info("Deleted ticket: " + client + " : " + fromPlanet + " : " + toPlanet);
    }

}
