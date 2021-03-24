package com.transportInc.comparators;

import com.transportInc.models.vehicles.contracts.IVehicle;

import java.util.Comparator;

public class VehicleTypeComparator implements Comparator<IVehicle> {
    public int compare(final IVehicle v1, final IVehicle v2) {
        return v1.getVehicleType().compareTo(v2.getVehicleType());
    }
}

