package com.transportInc.core.factories;

import com.transportInc.models.travel_administration.contracts.ITicket;
import com.transportInc.models.travel_administration.contracts.ITrip;
import com.transportInc.models.vehicles.contracts.IAirplane;
import com.transportInc.models.vehicles.contracts.IBus;
import com.transportInc.models.vehicles.contracts.ITrain;
import com.transportInc.models.vehicles.contracts.IVehicle;

public interface ITransportIncFactory {
    IAirplane createAirplane(double pricePerKilometer, int passengerCapacity, boolean hasFreeFood);

    IBus createBus(double pricePerKilometer, int passengerCapacity);

    ITrain createTrain(double pricePerKilometer, int passengerCapacity, int numberOfCarts);

    ITrip createTrip(String startingPoint, String destination, int distance, IVehicle vehicle);

    ITicket createTicket(ITrip trip, double administrativeCosts);
}
