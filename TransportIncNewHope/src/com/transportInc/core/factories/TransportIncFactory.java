package com.transportInc.core.factories;

import com.transportInc.models.travel_administration.contracts.ITicket;
import com.transportInc.models.travel_administration.contracts.ITrip;
import com.transportInc.models.travel_administration.Ticket;
import com.transportInc.models.travel_administration.Trip;
import com.transportInc.models.vehicles.Airplane;
import com.transportInc.models.vehicles.Bus;
import com.transportInc.models.vehicles.contracts.IAirplane;
import com.transportInc.models.vehicles.contracts.IBus;
import com.transportInc.models.vehicles.contracts.ITrain;
import com.transportInc.models.vehicles.contracts.IVehicle;
import com.transportInc.models.vehicles.Train;

public class TransportIncFactory implements ITransportIncFactory {
    public IAirplane createAirplane(double pricePerKilometer, int passengerCapacity, boolean hasFreeFood) {
        return new Airplane(pricePerKilometer, passengerCapacity, hasFreeFood);
    }

    public IBus createBus(double pricePerKilometer, int passengerCapacity) {
        return new Bus(pricePerKilometer, passengerCapacity);
    }

    public ITrain createTrain(double pricePerKilometer, int passengerCapacity, int numberOfCarts) {
        return new Train(pricePerKilometer, passengerCapacity, numberOfCarts);
    }

    public ITrip createTrip(String startPoint, String destination, int distance, IVehicle vehicle)
    {
        return new Trip(startPoint, destination, distance, vehicle);
    }

    public ITicket createTicket(ITrip trip, double administrativeCosts)
    {
        return new Ticket(trip, administrativeCosts);
    }
}
