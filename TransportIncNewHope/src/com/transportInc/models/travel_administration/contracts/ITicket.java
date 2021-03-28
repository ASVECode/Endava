package com.transportInc.models.travel_administration.contracts;

public interface ITicket {
    double getPrice();

    ITrip getTrip ();

    double calculatePrice();

    int getIndexTrip();
}
