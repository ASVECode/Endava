package Models.Vehicles;

import Models.Constants;
import Models.Enums.VehicleType;
import Models.Vehicles.Contracts.ITrain;

import java.security.InvalidParameterException;

public class Train extends Vehicle implements ITrain {
    private int numberOfCarts;
    private int passengerCapacity;

    public Train(double pricePerKilometer, int passengerCapacity, int numberOfCarts) throws InvalidParameterException {
        super(pricePerKilometer, passengerCapacity);
        this.setNumberOfCarts(numberOfCarts);
    }

    protected void setNumberOfCarts(int numberOfCarts) throws InvalidParameterException {
        if (numberOfCarts < Constants.CartMinCount || numberOfCarts > Constants.CartMaxCount) {
            throw new InvalidParameterException("A train cannot have less than " + Constants.CartMinCount + " cart or more than " + Constants.CartMaxCount + " carts.");
        }
        this.numberOfCarts = numberOfCarts;
    }

    @Override
    public int getNumberOfCarts() {
        return this.numberOfCarts;
    }

    @Override
    protected void setPassengerCapacity(int passengerCapacity) throws InvalidParameterException {
        if (passengerCapacity < Constants.TrainMinPassengers || passengerCapacity > Constants.TrainMaxPassengers) {
            throw new InvalidParameterException("A train cannot have less than " + Constants.TrainMinPassengers + " passengers or more than " + Constants.TrainMaxPassengers + " passengers.");
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
        return "Train";
    }

    @Override
    public String toString() {
        return super.toString() +
                "Carts amount: " + this.getNumberOfCarts() + '\n';
    }
}
