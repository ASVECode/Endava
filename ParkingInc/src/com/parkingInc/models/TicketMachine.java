package com.parkingInc.models;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class TicketMachine {
    private final double pricePerHour = 3.50;

    public ParkingTicket createParkingTicket(String vehicleLicencePlateNumber) {
        ParkingTicket ticket = new ParkingTicket();
        ticket.setTimestampEnter(LocalDateTime.now());
        ticket.setTicketID(vehicleLicencePlateNumber);
        return ticket;
    }

    public ParkingTicket calculateTicketPrice(ParkingTicket ticket) {
        LocalDateTime timestampNow = LocalDateTime.now();
        ticket.setTimestampExit(timestampNow);
        LocalDateTime ticketTimestampEnter = ticket.getTimestampEnter();
        Random rand = new Random();
        int imagenaryHours = rand.nextInt(8);
        double hours = ticketTimestampEnter.until(timestampNow, ChronoUnit.HOURS)+imagenaryHours;
        double ticketPrice = hours * pricePerHour;
        ticket.setTicketPrice(ticketPrice);
        return ticket;
    }
}
