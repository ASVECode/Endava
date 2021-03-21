package Models.TravelAdministration;

import Models.Constants;
import Models.TravelAdministration.Contracts.ITrip;
import Models.Vehicles.Contracts.IVehicle;

import java.security.InvalidParameterException;

public class Trip implements ITrip {
    private String startPoint;
    private String destination;
    private int distance;
    protected IVehicle vehicle;

    public Trip(String startPoint, String destination, int distance, IVehicle vehicle) throws InvalidParameterException {
        this.setStartPoint(startPoint);
        this.setDestination(destination);
        this.setDistance(distance);
        this.setVehicle(vehicle);
    }

    protected void setStartPoint(String startPoint) throws InvalidParameterException {
        int startPointLength = startPoint.length();
        if (startPointLength < Constants.StartingPointMinLength || startPointLength > Constants.StartingPointMaxLength) {
            throw new InvalidParameterException("The StartingPoint's length cannot be less than " + Constants.StartingPointMinLength + " or more than " + Constants.StartingPointMaxLength + " characters long.");
        }
        this.startPoint = startPoint;
    }

    @Override
    public String getStartPoint() {
        return this.startPoint;
    }

    protected void setDestination(String destination) throws InvalidParameterException {
        int destinationLength = destination.length();
        if (destinationLength < Constants.DestinationMinLength || destinationLength > Constants.DestinationMaxLength) {
            throw new InvalidParameterException("The Destination's length cannot be less than " + Constants.DestinationMinLength + " or more than " + Constants.DestinationMaxLength + " characters long.");
        }
        this.destination = destination;
    }

    @Override
    public String getDestination() {
        return this.destination;
    }

    protected void setDistance(int distance) throws InvalidParameterException {
        if (distance < Constants.DistanceMinInterval || distance > Constants.DistanceMaxInterval) {
            throw new InvalidParameterException("The Distance length cannot be less than " + Constants.DistanceMinInterval + " or more than " + Constants.DistanceMaxInterval + " km long.");
        }
        this.distance = distance;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    protected void setVehicle(IVehicle vehicle) throws InvalidParameterException {
        if (vehicle == null) {
            throw new InvalidParameterException("Vehicle can not be null!");
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
                "vehicle: " + vehicle;
    }
}
