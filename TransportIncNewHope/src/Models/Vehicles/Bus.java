package Models.Vehicles;

import Models.Constants;
import Models.Enums.VehicleType;
import Models.Vehicles.Contracts.IBus;

import java.security.InvalidParameterException;

public class Bus extends Vehicle implements IBus {
    int passengerCapacity;

    public Bus(double pricePerKilometer, int passengerCapacity) throws InvalidParameterException {
        super(pricePerKilometer, passengerCapacity);
    }

    @Override
    protected void setPassengerCapacity(int passengerCapacity) throws InvalidParameterException {
        if (passengerCapacity < Constants.BusMinPassengers || passengerCapacity > Constants.BusMaxPassengers) {
            throw new InvalidParameterException("A bus cannot have less than " + Constants.BusMinPassengers + " passengers or more than " + Constants.BusMaxPassengers + " passengers.");
        }
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.Land;
    }

    @Override
    protected String getVehicleName() {
        return "Bus";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
