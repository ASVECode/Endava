package Core.Factories;

import Models.TravelAdministration.Contracts.ITicket;
import Models.TravelAdministration.Contracts.ITrip;
import Models.Vehicles.Contracts.IAirplane;
import Models.Vehicles.Contracts.IBus;
import Models.Vehicles.Contracts.ITrain;
import Models.Vehicles.Contracts.IVehicle;

public interface ITransportIncFactory {
    IAirplane createAirplane(double pricePerKilometer, int passengerCapacity, boolean hasFreeFood);

    IBus createBus(double pricePerKilometer, int passengerCapacity);

    ITrain createTrain(double pricePerKilometer, int passengerCapacity, int numberOfCarts);

    ITrip createTrip(String startingPoint, String destination, int distance, IVehicle vehicle);

    ITicket createTicket(ITrip trip, double administrativeCosts);
}
