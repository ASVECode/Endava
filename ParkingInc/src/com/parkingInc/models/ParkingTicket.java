package com.parkingInc.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicket {
    private String ticketID;
    private LocalDateTime timestampEnter;
    private LocalDateTime timestampExit;
    private double price;

    public boolean setTimestampEnter(LocalDateTime timestampEnter) throws IllegalArgumentException {
        if (timestampEnter == null) {
            throw new IllegalArgumentException();
        }
        this.timestampEnter = timestampEnter;
        return true;
    }

    public LocalDateTime getTimestampEnter() {
        return this.timestampEnter;
    }

    public void setTimestampExit(LocalDateTime timestampExit) throws IllegalArgumentException {
        if (timestampExit == null) {
            throw new IllegalArgumentException();
        }
        this.timestampExit = timestampExit;
    }

    public LocalDateTime getTimestampExit() {
        return this.timestampExit;
    }

    public void setTicketID(String ticketID) throws IllegalArgumentException {
        if (ticketID.isEmpty() || ticketID.isBlank() || ticketID == null) {
            throw new IllegalArgumentException();
        }
        this.ticketID = ticketID;
    }

    public String getTicketID() {
        return this.ticketID;
    }

    public void setTicketPrice(double ticketPrice) throws IllegalArgumentException {
        if (ticketPrice < 0) {
            throw new IllegalArgumentException();
        }
        this.price = ticketPrice;
    }

    public double getPrice(){
        return this.price;
    }
}
