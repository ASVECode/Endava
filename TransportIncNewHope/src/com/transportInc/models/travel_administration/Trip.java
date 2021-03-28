package com.transportInc.models.travel_administration;

import com.transportInc.models.Constants;
import com.transportInc.models.travel_administration.contracts.ITrip;
import com.transportInc.models.vehicles.contracts.IVehicle;

public class Trip implements ITrip {
    private String startPoint;
    private String destination;
    private int distance;
    protected IVehicle vehicle;

    public Trip(String startPoint, String destination, int distance, IVehicle vehicle) throws IllegalArgumentException {
        this.setStartPoint(startPoint);
        this.setDestination(destination);
        this.setDistance(distance);
        this.setVehicle(vehicle);
    }

    protected void setStartPoint(String startPoint) throws IllegalArgumentException {
        int startPointLength = startPoint.length();
        if (startPointLength < Constants.STARTING_POINT_MIN_LENGTH || startPointLength > Constants.STARTING_POINT_MAX_LENGTH) {
            throw new IllegalArgumentException("The StartingPoint's length cannot be less than " + Constants.STARTING_POINT_MIN_LENGTH + " or more than " + Constants.STARTING_POINT_MAX_LENGTH + " characters long.");
        }
        this.startPoint = startPoint;
    }

    @Override
    public String getStartPoint() {
        return this.startPoint;
    }

    protected void setDestination(String destination) throws IllegalArgumentException {
        int destinationLength = destination.length();
        if (destinationLength < Constants.DESTINATION_MIN_LENGTH || destinationLength > Constants.DESTINATION_MAX_LENGTH) {
            throw new IllegalArgumentException("The Destination's length cannot be less than " + Constants.DESTINATION_MIN_LENGTH + " or more than " + Constants.DESTINATION_MAX_LENGTH + " characters long.");
        }
        this.destination = destination;
    }

    @Override
    public String getDestination() {
        return this.destination;
    }

    protected void setDistance(int distance) throws IllegalArgumentException {
        if (distance < Constants.DISTANCE_MIN_INTERVAL || distance > Constants.DISTANCE_MAX_INTERVAL) {
            throw new IllegalArgumentException("The Distance length cannot be less than " + Constants.DISTANCE_MIN_INTERVAL + " or more than " + Constants.DISTANCE_MAX_INTERVAL + " km long.");
        }
        this.distance = distance;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    protected void setVehicle(IVehicle vehicle) throws IllegalArgumentException {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle can not be null!");
        }
        this.vehicle = vehicle;
    }

    @Override
    public IVehicle getVehicle() {
        return this.vehicle;
    }

    @Override
    public double calculateTripPrice() {
        double pricePerKilometer = this.vehicle.getPricePerKilometer();
        double price = (this.distance * pricePerKilometer);
        return price;
    }

    @Override
    public String toString() {
        return "Trip" + '\n' +
                "startPoint: " + startPoint + '\n' +
                "destination: " + destination + '\n' +
                "distance: " + distance + '\n' +
                 vehicle;
    }
}
