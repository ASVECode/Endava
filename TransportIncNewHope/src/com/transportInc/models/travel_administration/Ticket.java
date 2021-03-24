package com.transportInc.models.travel_administration;

import com.transportInc.models.travel_administration.contracts.ITicket;
import com.transportInc.models.travel_administration.contracts.ITrip;

public class Ticket implements ITicket {
    protected double price;
    protected ITrip trip;

    public Ticket(ITrip trip, double price) throws IllegalArgumentException {
        this.setPrice(price);
        this.setTrip(trip);
    }

    protected void setPrice(double price) throws IllegalArgumentException {
        if (price < 0) {
            throw new IllegalArgumentException("Ticket price can not be less than 0.");
        }
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    protected void setTrip(ITrip trip) throws IllegalArgumentException {
        if (trip == null) {
            throw new IllegalArgumentException("Trip can not be null!");
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
