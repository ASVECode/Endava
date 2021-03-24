package com.transportInc.models.vehicles.contracts;

import com.transportInc.models.enums.VehicleType;

public interface IVehicle {
    int getPassengerCapacity();

    double getPricePerKilometer();

    VehicleType getVehicleType();
}
