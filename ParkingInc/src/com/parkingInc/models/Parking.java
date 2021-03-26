package com.parkingInc.models;

import com.parkingInc.models.vehicles.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Parking {
    private final int amountOfCarSpaces = 200;
    private final int amountOfAirplaneSpaces = 50;
    private final int amountOfTrainSpaces = 50;

    private HashMap<String, Vehicle> cars;
    private HashMap<String, Vehicle> airplanes;
    private HashMap<String, Vehicle> trains;

    private HashMap<Integer, ParkingTicket> tickets;

    public Parking() {
        this.cars = new HashMap<String, Vehicle>();
        this.airplanes = new HashMap<String, Vehicle>();
        this.trains = new HashMap<String, Vehicle>();
        this.tickets = new HashMap<Integer, ParkingTicket>();
    }

    public boolean vehicleEnter(Vehicle vehicle) {

        switch (vehicle.getVehicleName()) {
            case CAR: {
                if (this.cars.size() == amountOfCarSpaces) {
                    return false;
                }
            }

            case AIRPLANE: {
                if (this.airplanes.size() == amountOfAirplaneSpaces) {
                    return false;
                }
            }

            case TRAIN: {
                if (this.trains.size() == amountOfTrainSpaces) {
                    return false;
                }
            }
        }
        return true;
    }

    public ParkingTicket entryVehicle(Vehicle vehicle) {
        ParkingTicket ticket = new ParkingTicket(vehicle, LocalDateTime.now());
        vehicle.setParkingTicket(ticket);
        ticket.setVehicle(vehicle);

        switch (vehicle.getVehicleName()) {
            case AIRPLANE: {
                airplanes.put(vehicle.getLicencePlateNumber(), vehicle);
                break;
            }
            case CAR: {
                cars.put(vehicle.getLicencePlateNumber(), vehicle);
                break;
            }
            case TRAIN: {
                trains.put(vehicle.getLicencePlateNumber(), vehicle);
                break;
            }
        }
        tickets.put(ticket.getTicketID(), ticket);
        return ticket;
    }

    public double vehicleExit(Vehicle vehicle) {
        double pricePerHour = 3.50;
        double hours = Duration.between(vehicle.getParkingTicket().getTimestampEnter(), LocalDateTime.now()).toHours();
        if(hours<2) return pricePerHour;
        double totalAmount = hours * pricePerHour;
        return totalAmount;
    }

}
