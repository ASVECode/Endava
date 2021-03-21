package Models.Vehicles;

import Models.Enums.VehicleType;
import Models.Vehicles.Contracts.IVehicle;

import java.security.InvalidParameterException;

public abstract class Vehicle implements IVehicle {
    private double pricePerKilometer;
    private int passengerCapacity;

    public Vehicle(double pricePerKilometer, int passengerCapacity) throws InvalidParameterException {
        this.setPricePerKilometer(pricePerKilometer);
        this.setPassengerCapacity(passengerCapacity);
    }

    protected void setPassengerCapacity(int passengerCapacity) throws InvalidParameterException {
        if (passengerCapacity < 1 || passengerCapacity > 600) {
            throw new InvalidParameterException("A vehicle with less than 1 passengers or more than 600 passengers cannot exist!");
        }
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }

    protected void setPricePerKilometer(double pricePerKilometer) throws InvalidParameterException {
        if (pricePerKilometer < 0.20 || pricePerKilometer > 3){
            throw new InvalidParameterException("A vehicle with a price per kilometer lower than $0.20 or higher than $3.00 cannot exist!");
        }
        this.pricePerKilometer = pricePerKilometer;
    }

    @Override
    public double getPricePerKilometer() {
        return this.pricePerKilometer;
    }

    @Override
    public abstract VehicleType getVehicleType();

    protected abstract String getVehicleName();

    @Override
    public String toString() {
        return this.getVehicleName() + '\n' +
                "Passenger capacity: " + this.getPassengerCapacity() + '\n' +
                "Price per kilometer: " + this.getPricePerKilometer() + '\n' +
                "Vehicle type: " + this.getVehicleType()+ '\n';
    }
}
