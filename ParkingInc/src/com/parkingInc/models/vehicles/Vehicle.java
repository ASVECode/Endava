package com.parkingInc.models.vehicles;

import com.parkingInc.models.enums.VehicleName;

import java.util.UUID;

public class Vehicle {
    private String licencePlateNumber;
    private String parkingTicketID;
    private VehicleName vehicleName;

    public Vehicle(VehicleName vehicleName){
        this.licencePlateNumber = UUID.randomUUID().toString();
        this.vehicleName = vehicleName;
    }

    public String getLicencePlateNumber(){
        return this.licencePlateNumber;
    }

    public void setParkingTicketID(String parkingTicketID) throws IllegalArgumentException {
        if (parkingTicketID.isEmpty() || parkingTicketID.isBlank() || parkingTicketID == null) {
            throw new IllegalArgumentException();
        }
        this.parkingTicketID = parkingTicketID;
    }

    public VehicleName getVehicleName() {
        return vehicleName;
    }

    public String getParkingTicketID() {
        return this.parkingTicketID;
    }
}
