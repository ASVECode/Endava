package Models.TravelAdministration.Contracts;

import Models.Vehicles.Contracts.IVehicle;

public interface ITrip {

    String getStartPoint();

    String getDestination();

    int getDistance();

    IVehicle getVehicle();

    double calculateTripPrice();
}
