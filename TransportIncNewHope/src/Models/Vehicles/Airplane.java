package Models.Vehicles;

import Models.Enums.VehicleType;
import Models.Vehicles.Contracts.IAirplane;

import java.security.InvalidParameterException;

public class Airplane extends Vehicle implements IAirplane {

    private boolean hasFreeFood;

    public Airplane(double pricePerKilometer, int passengerCapacity, boolean hasFreeFood) throws InvalidParameterException {
        super(pricePerKilometer, passengerCapacity);
        this.hasFreeFood = hasFreeFood;
    }

    @Override
    public boolean getHasFreeFood() {
        return this.hasFreeFood;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.Air;
    }

    @Override
    protected String getVehicleName() {
        return "Airplane";
    }
}
