package com.transportInc.comparators;

import com.transportInc.models.travel_administration.contracts.ITicket;

import java.util.Comparator;

public class TicketPriceComparator implements Comparator<ITicket> {
    public int compare( final ITicket t1, final ITicket t2) {
        return Double.compare(t1.getPrice(), t2.getPrice());
    }
}