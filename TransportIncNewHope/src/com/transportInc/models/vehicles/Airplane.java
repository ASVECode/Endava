package com.transportInc.models.vehicles;

import com.transportInc.models.enums.VehicleName;
import com.transportInc.models.enums.VehicleType;
import com.transportInc.models.vehicles.contracts.IAirplane;

public class Airplane extends Vehicle implements IAirplane {

    private boolean hasFreeFood;

    public Airplane(double pricePerKilometer, int passengerCapacity, boolean hasFreeFood) throws IllegalArgumentException {
        super(pricePerKilometer, passengerCapacity);
        this.hasFreeFood = hasFreeFood;
    }

    @Override
    public boolean getHasFreeFood() {
        return this.hasFreeFood;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.AIR;
    }

    @Override
    public VehicleName getVehicleName() {
        return VehicleName.AIRPLANE;
    }
}
