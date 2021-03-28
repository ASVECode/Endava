package com.transportInc.models.vehicles;

import com.transportInc.models.Constants;
import com.transportInc.models.enums.VehicleName;
import com.transportInc.models.enums.VehicleType;
import com.transportInc.models.vehicles.contracts.IBus;

public class Bus extends Vehicle implements IBus {
    int passengerCapacity;

    public Bus(double pricePerKilometer, int passengerCapacity) throws IllegalArgumentException {
        super(pricePerKilometer, passengerCapacity);
    }

    @Override
    protected void setPassengerCapacity(int passengerCapacity) throws IllegalArgumentException {
        if (passengerCapacity < Constants.BUS_MIN_PASSENGER || passengerCapacity > Constants.BUS_MAX_PASSENGER) {
            throw new IllegalArgumentException("A bus cannot have less than " + Constants.BUS_MIN_PASSENGER + " passengers or more than " + Constants.BUS_MAX_PASSENGER + " passengers.");
        }
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.LAND;
    }

    @Override
    public VehicleName getVehicleName() {
        return VehicleName.BUS;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
