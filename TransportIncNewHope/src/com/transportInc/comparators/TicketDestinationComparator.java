package com.transportInc.comparators;

import com.transportInc.models.travel_administration.contracts.ITicket;

import java.util.Comparator;

public class TicketDestinationComparator implements Comparator<ITicket> {
    public int compare(final ITicket t1, final ITicket t2) {
        return t1.getTrip().getDestination().compareTo(t2.getTrip().getDestination());
    }
}