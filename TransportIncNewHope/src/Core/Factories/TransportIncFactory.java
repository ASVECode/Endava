package Core.Factories;

import Models.TravelAdministration.Contracts.ITicket;
import Models.TravelAdministration.Contracts.ITrip;
import Models.TravelAdministration.Ticket;
import Models.TravelAdministration.Trip;
import Models.Vehicles.Airplane;
import Models.Vehicles.Bus;
import Models.Vehicles.Contracts.IAirplane;
import Models.Vehicles.Contracts.IBus;
import Models.Vehicles.Contracts.ITrain;
import Models.Vehicles.Contracts.IVehicle;
import Models.Vehicles.Train;

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
