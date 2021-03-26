package com.parkingInc.models;

import com.parkingInc.models.vehicles.Vehicle;

import java.time.LocalDateTime;

public class ParkingTicket {

    private static int uniqueID = 1;

    private int ticketID;
    private Vehicle vehicle;
    private LocalDateTime timestampEnter;

    public ParkingTicket(Vehicle vehicle, LocalDateTime timestampEnter) {
        this.ticketID = ticketID++;
        this.vehicle = vehicle;
        this.timestampEnter = timestampEnter;
    }

    public LocalDateTime getTimestampEnter() {
        return this.timestampEnter;
    }

    public void setTimestampEnter(LocalDateTime timestampEnter) throws IllegalArgumentException {
        if (timestampEnter == null) {
            throw new IllegalArgumentException();
        }
        this.timestampEnter = timestampEnter;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
