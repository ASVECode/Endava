package com.transportInc.models.travel_administration.contracts;

import com.transportInc.models.vehicles.contracts.IVehicle;

public interface ITrip {

    String getStartPoint();

    String getDestination();

    int getDistance();

    IVehicle getVehicle();

    double calculateTripPrice();
}
