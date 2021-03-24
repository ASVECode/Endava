package com.transportInc.comparators;

import com.transportInc.models.vehicles.contracts.IVehicle;

import java.util.Comparator;

public class VehiclePricePerKilometerComparator implements Comparator<IVehicle> {
    public int compare(final IVehicle v1, final IVehicle v2) {
        return Double.compare(v1.getPricePerKilometer(), v2.getPricePerKilometer());
    }
}
