package com.parkingInc.models;

import com.parkingInc.models.enums.VehicleName;
import com.parkingInc.models.vehicles.Vehicle;

import java.util.HashMap;

public class Parking {
    private final int amountOfCarSpaces = 200;
    private final int amountOfAirplaneSpaces = 50;
    private final int amountOfTrainSpaces = 50;


    private HashMap<String, Vehicle> cars;
    private HashMap<String, Vehicle> airplanes;
    private HashMap<String, Vehicle> trains;


    private HashMap<String, ParkingTicket > tickets;

    private TicketMachine ticketMachine;

    public Parking() {
        this.cars = new HashMap<String, Vehicle>();
        this.airplanes = new HashMap<String, Vehicle>();
        this.trains = new HashMap<String, Vehicle>();

        this.tickets = new HashMap<String, ParkingTicket>();
        this.ticketMachine = new TicketMachine();
    }

    public boolean vehicleEnter(Vehicle vehicle) {

        switch (vehicle.getVehicleName()){
            case CAR:{
                if (this.cars.size() == amountOfCarSpaces) {
                    return false;
                }

            }

            case AIRPLANE:{
                if (this.airplanes.size() == amountOfCarSpaces) {
                    return false;
                }
            }

            case TRAIN:{
                if (this.trains.size() == amountOfCarSpaces) {
                    return false;
                }
            }
        }

        ParkingTicket ticket = this.ticketMachine.createParkingTicket(vehicle.getLicencePlateNumber());
        vehicle.setParkingTicketID(ticket.getTicketID());
        tickets.put(ticket.getTicketID(),ticket);

        switch (vehicle.getVehicleName()){
            case CAR:{
                this.cars.put(ticket.getTicketID(), vehicle);
                break;
            }
            case AIRPLANE:{
                this.airplanes.put(ticket.getTicketID(), vehicle);
                break;
            }
            case TRAIN:{
                this.trains.put(ticket.getTicketID(), vehicle);
                break;
            }
        }

        return true;
    }

    public double vehicleExit(String vehicleLicencePlateNumber, VehicleName vehicleName){
        System.out.print("HI");
        ParkingTicket ticket = tickets.get(vehicleLicencePlateNumber);
        ticket = this.ticketMachine.calculateTicketPrice(ticket);

        return ticket.getPrice();
    }
}
