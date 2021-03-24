package com.transportInc.models.vehicles;

import com.transportInc.models.enums.VehicleName;
import com.transportInc.models.enums.VehicleType;


public abstract class Vehicle {
    private double pricePerKilometer;
    private int passengerCapacity;

    public Vehicle(double pricePerKilometer, int passengerCapacity) throws IllegalArgumentException {
        this.setPricePerKilometer(pricePerKilometer);
        this.setPassengerCapacity(passengerCapacity);
    }

    protected void setPassengerCapacity(int passengerCapacity) throws IllegalArgumentException {
        if (passengerCapacity < 1 || passengerCapacity > 600) {
            throw new IllegalArgumentException("A vehicle with less than 1 passengers or more than 600 passengers cannot exist!");
        }
        this.passengerCapacity = passengerCapacity;
    }


    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }

    protected void setPricePerKilometer(double pricePerKilometer) throws IllegalArgumentException {
        if (pricePerKilometer < 0.20 || pricePerKilometer > 3){
            throw new IllegalArgumentException("A vehicle with a price per kilometer lower than $0.20 or higher than $3.00 cannot exist!");
        }
        this.pricePerKilometer = pricePerKilometer;
    }

    public double getPricePerKilometer() {
        return this.pricePerKilometer;
    }

    public abstract VehicleType getVehicleType();

    protected abstract VehicleName getVehicleName();

    public String toString() {
        return  "Vehicle name: " + this.getVehicleName() + '\n' +
                "Passenger capacity: " + this.getPassengerCapacity() + '\n' +
                "Price per kilometer: " + this.getPricePerKilometer() + '\n' +
                "Vehicle type: " + this.getVehicleType()+ '\n';
    }
}
