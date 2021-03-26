package com.parkingInc.core;

import com.parkingInc.core.providers.ConsoleWriter;
import com.parkingInc.core.providers.FileReader;
import com.parkingInc.models.Parking;
import com.parkingInc.models.ParkingTicket;
import com.parkingInc.models.enums.VehicleName;
import com.parkingInc.models.vehicles.Vehicle;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Engine {
    private static final Engine instanceHolder = new Engine();

    private final File file;
    private FileReader reader;
    private ConsoleWriter writer;

    private Parking parking;
    private HashMap<String, Vehicle> vehicles;


    private Engine() {
        this.file = new File("src/com/parkingInc/input_files/input_commands.txt");
        this.reader = new FileReader(file);
        this.writer = new ConsoleWriter();
        this.parking = new Parking();
        this.vehicles = new HashMap<String, Vehicle>();

    }

    public static Engine getInstanceHolder() {
        return instanceHolder;
    }

    public void start() {
        List<String> registrations = reader.readFile(file.getName());
        for (int i = 0, sz = registrations.size(); i < sz; i++) {
            String registration = registrations.get(i);
            String[] registrationDetails = parseRegistration(registration);
            executeCommand(registrationDetails);
        }
    }

    private void executeCommand(String[] registrationDetails) {
        String command = registrationDetails[0];
        String licencePlateNumber = registrationDetails[1];

        switch (command) {
            case "car": {
                if (vehicles.containsKey(licencePlateNumber)) {
                    exitVehicleCommand(registrationDetails);
                } else {
                    tryEnterCarCommand(registrationDetails);
                }
                break;
            }

            case "airplane": {
                if (vehicles.containsKey(licencePlateNumber)) {
                    exitVehicleCommand(registrationDetails);
                } else {
                    tryEnterAirplaneCommand(registrationDetails);
                }
                break;
            }
            case "train": {
                if (vehicles.containsKey(licencePlateNumber)) {
                    exitVehicleCommand(registrationDetails);
                } else {
                    tryEnterTrainCommand(registrationDetails);
                }
                break;
            }
            case "list": {
                listAllVehicleCommand(vehicles);
                break;
            }
            case "list cars":{

            }
        }
    }

    String[] parseRegistration(String registration) {
        String[] registrationDetails = registration.split(" ");
        return registrationDetails;
    }

    private void tryEnterCarCommand(String[] registrationDetails) {
        Vehicle car = new Vehicle(VehicleName.CAR, registrationDetails[1]);
        boolean hasEnter = this.parking.vehicleEnter(car);
        if (hasEnter) {
            writer.writeLine("Car with registration plate: " + car.getLicencePlateNumber() + " entered the parking" + "\n");
            ParkingTicket ticket = parking.entryVehicle(car);
            vehicles.put(car.getLicencePlateNumber(), car);
        } else {
            writer.writeLine("Car parking is full.There are no free car spaces" + "\n");
        }
    }

    private void tryEnterAirplaneCommand(String[] commandParts) {
        Vehicle airplane = new Vehicle(VehicleName.AIRPLANE, commandParts[1]);
        boolean hasEnter = this.parking.vehicleEnter(airplane);
        if (hasEnter) {
            writer.writeLine("Airplane with registration plate: " + airplane.getLicencePlateNumber() + " entered the parking" + "\n");
            ParkingTicket ticket = parking.entryVehicle(airplane);
            vehicles.put(airplane.getLicencePlateNumber(), airplane);
        } else {
            writer.writeLine("Airplane parking is full.There are no free airplane spaces" + "\n");
        }
    }

    private void tryEnterTrainCommand(String[] commandParts) {
        Vehicle train = new Vehicle(VehicleName.TRAIN, commandParts[1]);
        boolean hasEnter = this.parking.vehicleEnter(train);
        if (hasEnter) {
            writer.writeLine("Train with registration plate: " + train.getLicencePlateNumber() + " entered the parking" + "\n");
            ParkingTicket ticket = parking.entryVehicle(train);
            vehicles.put(train.getLicencePlateNumber(), train);
        } else {
            writer.writeLine("Train parking is full.There are no free train spaces" + "\n");
        }
    }

    private void exitVehicleCommand(String[] registrationDetails) {
        String vehicleLicencePlateNumber = registrationDetails[1];
        var vehicle = vehicles.get(vehicleLicencePlateNumber);
        double price = parking.vehicleExit(vehicle);
        vehicles.remove(vehicleLicencePlateNumber);
        writer.writeLine(vehicle.getVehicleName() +
                " with registration plate " + vehicle.getLicencePlateNumber() +
                " successfully left the parking. Total price is: " + price + " $" + "\n");
    }

    private void listAllVehicleCommand(HashMap<String, Vehicle> vehicles) {
        if (vehicles.size() == 0) {
            writer.writeLine("There no vehicles at the parking");
        } else {
            writer.writeLine("List of vehicles at the parking");
            writer.writeLine("...............");
            for (String key : vehicles.keySet()) {
                writer.writeLine(vehicles.get(key).getVehicleName() + " with registration plate " + vehicles.get(key).getLicencePlateNumber());
            }
            writer.writeLine("..............." + "\n");
        }
    }

    private void listAllCarsCommand(HashMap<String, Vehicle> vehicles) {
        writer.writeLine("List of vehicles at the parking");
        writer.writeLine("...............");
        for (String key : vehicles.keySet()) {
            if (vehicles.get(key).getVehicleName() == VehicleName.CAR) {
                writer.writeLine("Car with registration plate " + vehicles.get(key).getLicencePlateNumber());
            }
        }
        writer.writeLine("..............." + "\n");
    }

}
