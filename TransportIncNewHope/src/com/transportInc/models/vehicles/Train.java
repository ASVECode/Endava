package com.transportInc.models.vehicles;

import com.transportInc.models.Constants;
import com.transportInc.models.enums.VehicleName;
import com.transportInc.models.enums.VehicleType;
import com.transportInc.models.vehicles.contracts.ITrain;

public class Train extends Vehicle implements ITrain {
    private int numberOfCarts;
    private int passengerCapacity;

    public Train(double pricePerKilometer, int passengerCapacity, int numberOfCarts) throws IllegalArgumentException {
        super(pricePerKilometer, passengerCapacity);
        this.setNumberOfCarts(numberOfCarts);
    }

    protected void setNumberOfCarts(int numberOfCarts) throws IllegalArgumentException {
        if (numberOfCarts < Constants.CART_MIN_COUNT || numberOfCarts > Constants.CART_MAX_COUNT) {
            throw new IllegalArgumentException("A train cannot have less than " + Constants.CART_MIN_COUNT + " cart or more than " + Constants.CART_MAX_COUNT + " carts.");
        }
        this.numberOfCarts = numberOfCarts;
    }

    @Override
    public int getNumberOfCarts() {
        return this.numberOfCarts;
    }

    @Override
    protected void setPassengerCapacity(int passengerCapacity) throws IllegalArgumentException {
        if (passengerCapacity < Constants.TRAIN_MIN_PASSENGER || passengerCapacity > Constants.TRAIN_MAX_PASSENGER) {
            throw new IllegalArgumentException("A train cannot have less than " + Constants.TRAIN_MIN_PASSENGER + " passengers or more than " + Constants.TRAIN_MAX_PASSENGER + " passengers.");
        }
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.LAND;
    }

    @Override
    protected VehicleName getVehicleName() {
        return VehicleName.TRAIN;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Carts amount: " + this.getNumberOfCarts() + '\n';
    }
}
