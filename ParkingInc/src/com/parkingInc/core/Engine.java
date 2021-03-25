package com.parkingInc.core;

import com.parkingInc.core.providers.ConsoleWriter;
import com.parkingInc.core.providers.FileReader;
import com.parkingInc.models.Parking;
import com.parkingInc.models.enums.VehicleName;
import com.parkingInc.models.vehicles.Vehicle;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {
    private static final Engine instanceHolder = new Engine();

    private final File file;
    private FileReader reader;
    private ConsoleWriter writer;

    private Parking parking;
    private HashMap<String, VehicleName> vehicles;

    // writer
    // command parser

    private Engine() {
        this.file = new File("src/com/parkingInc/input_files/input_commands.txt");
        this.reader = new FileReader(file);
        this.writer = new ConsoleWriter();
        this.parking = new Parking();
        this.vehicles = new HashMap<String, VehicleName>();
    }

    public static Engine getInstanceHolder() {
        return instanceHolder;
    }

    public void start() {
        List<String> commands = reader.readFile(file.getName());
        for (int i = 0, sz = commands.size(); i < sz; i++) {
            String command = commands.get(i);
            String[] commandParts = parseCommand(command);
            executeCommand(commandParts);
        }
        // TODO
        // 1. create vehicles
        // 2. enter parking
        //2.1 if no spaces for vehicle print no spaces for type vehicles
        // 3. exit parking , print how much does vehicle owe
    }

    private void executeCommand(String[] commandParts) {
        String commandName = commandParts[0];

        switch (commandName) {
            case "tryentercar": {
                tryEnterCarCommand(commandParts);
                break;
            }

            case "exitcar": {
                exitVehicleCommand(commandParts);
                break;
            }

            case "exitairplane": {
                exitVehicleCommand(commandParts);
                break;
            }

            case "tryenterairplane": {
                exitVehicleCommand(commandParts);
                break;
            }
        }

    }

    String[] parseCommand(String command) {
        String[] commandParts = command.split(" ");
        return commandParts;
    }

    private void tryEnterCarCommand(String[] commandParts) {
        Vehicle car = new Vehicle(VehicleName.CAR);
        boolean hasEnter = this.parking.vehicleEnter(car);
        if (hasEnter) {
            writer.writeLine("Car entered parking");
            vehicles.put(car.getLicencePlateNumber(), VehicleName.CAR);
        } else {
            writer.writeLine("Car parking is full.There are no free car spaces");
        }
    }

    private void tryEnterAirplaneCommand() {
        Vehicle airplane = new Vehicle(VehicleName.AIRPLANE);
        boolean hasEnter = this.parking.vehicleEnter(airplane);
        if (hasEnter) {
            writer.writeLine("Aiplane entered parking");
        } else {
            writer.writeLine("Airplane parking is full.There are no free airplane spaces");
        }
    }

    private void exitVehicleCommand(String[] commandParts) {
        if (vehicles.size() == 0) {
            writer.writeLine("All vehicles already left");
        }

        Map.Entry<String, VehicleName> entry = vehicles.entrySet().iterator().next();
        String vehicleLicencePlateNumber = entry.getKey();
        VehicleName vehicleName = entry.getValue();
        double price = parking.vehicleExit(vehicleLicencePlateNumber, vehicleName);
        writer.writeLine("The driver owes : " + price);
        vehicles.remove(vehicleLicencePlateNumber);
    }
}
