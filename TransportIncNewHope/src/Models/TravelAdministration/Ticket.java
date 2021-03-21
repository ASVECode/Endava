package Models.TravelAdministration;

import Models.TravelAdministration.Contracts.ITicket;
import Models.TravelAdministration.Contracts.ITrip;
import java.security.InvalidParameterException;

public class Ticket implements ITicket {
    protected double price;
    protected ITrip trip;

    public Ticket(ITrip trip, double price) throws InvalidParameterException {
        this.setPrice(price);
        this.setTrip(trip);
    }

    protected void setPrice(double price) throws InvalidParameterException {
        if (price < 0) {
            throw new InvalidParameterException("Ticket price can not be less than 0.");
        }
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    protected void setTrip(ITrip trip) throws InvalidParameterException {
        if (trip == null) {
            throw new InvalidParameterException("Trip can not be null!");
        }
        this.trip = trip;
    }

    @Override
    public ITrip getTrip() {
        return this.trip;
    }

    @Override
    public double calculatePrice() {
        double vehiclePassengerCapacity = this.trip.getVehicle().getPassengerCapacity();
        double tripPrice = this.trip.calculateTripPrice();
        double ticketPrice = this.price + (tripPrice / vehiclePassengerCapacity);
        return ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket " + '\n' +
                "price: " + price + '\n' +
                "trip: " + trip;
    }
}
