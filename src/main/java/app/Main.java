package app;

import app.database.configuration.*;
import app.database.crud.*;
import app.database.entity.*;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Start program");

        Migration.flywayMigration();

        ClientCrudService clientCrudService = new ClientCrudService();
        Client client = new Client();
        client.setFirstname("Albina");
        client.setLastname("Kovalchuk");
        clientCrudService.save(client);
        clientCrudService.delete(client);

        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet planet = new Planet();
        planet.setId("NEP");
        planet.setName("Neptune");
        planetCrudService.save(planet);
        planetCrudService.delete(planet);

        TicketCrudService ticketCrudService = new TicketCrudService();
        Client clientTicket = clientCrudService.findById(5);
        Planet fromPlanet = planetCrudService.findById("MAR");
        Planet toPlanet = planetCrudService.findById("SAT");
        Ticket ticket = new Ticket();
        ticket.setClient(clientTicket);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticketCrudService.save(ticket);
        ticketCrudService.delete(ticket);

        HibernateUtil.getInstance().close();

        LOGGER.info("Finish program");
    }
}