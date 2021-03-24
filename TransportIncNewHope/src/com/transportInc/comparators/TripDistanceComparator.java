package com.transportInc.comparators;

import com.transportInc.models.travel_administration.contracts.ITrip;

import java.util.Comparator;

public class TripDistanceComparator implements Comparator<ITrip> {
    public int compare(final ITrip t1, final ITrip t2) {
        return t1.getDistance() - t2.getDistance();
    }
}
