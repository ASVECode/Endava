package com.transportInc.models.travel_administration;

import com.transportInc.models.travel_administration.contracts.ITicket;
import com.transportInc.models.travel_administration.contracts.ITrip;

public class Ticket implements ITicket {
    protected double price;
    protected ITrip trip;
    private int tripIndex;

    public Ticket(ITrip trip, double price, int tripIndex) throws IllegalArgumentException {
        this.setPrice(price);
        this.setTrip(trip);
        this.tripIndex = tripIndex;

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
    public int getIndexTrip() {
        return this.tripIndex;
    }

    protected void setTripIndex(int tripIndex) throws IllegalArgumentException {
        if (tripIndex < 0) {
            throw new IllegalArgumentException("Ticket index can not be less than 0.");
        }
        this.tripIndex = tripIndex;
    }

    @Override
    public String toString() {
        return "Ticket " + '\n' +
                "price: " + price + '\n' +
                "trip: " + trip.getDestination() + " indexTrip "
                + this.getIndexTrip();
    }
}
