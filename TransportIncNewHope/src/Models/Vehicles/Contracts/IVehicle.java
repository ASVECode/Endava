package Models.Vehicles.Contracts;

import Models.Enums.VehicleType;

public interface IVehicle {
    int getPassengerCapacity();

    double getPricePerKilometer();

    VehicleType getVehicleType();
}
