package com.parkingInc.models.vehicles;

import com.parkingInc.models.ParkingTicket;
import com.parkingInc.models.enums.VehicleName;

import java.util.UUID;

public class Vehicle {
    private String licencePlateNumber;
    private ParkingTicket parkingTicket;
    private VehicleName vehicleName;

    public Vehicle(VehicleName vehicleName, String licencePlateNumber){
        this.licencePlateNumber = licencePlateNumber;
        this.vehicleName = vehicleName;
    }

    public String getLicencePlateNumber(){
        return this.licencePlateNumber;
    }

    public void setParkingTicket(ParkingTicket ticket) throws IllegalArgumentException {
        this.parkingTicket = ticket;
    }

    public VehicleName getVehicleName() {
        return vehicleName;
    }

    public ParkingTicket getParkingTicket() {
        return this.parkingTicket;
    }
}
