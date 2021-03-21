package Core;

import Core.Contracts.IEngine;
import Core.Contracts.IReader;
import Core.Contracts.IWriter;
import Core.Factories.ITransportIncFactory;
import Core.Factories.TransportIncFactory;
import Core.Providers.ConsoleWriter;
import Core.Providers.FileReader;
import Models.TravelAdministration.Contracts.ITicket;
import Models.TravelAdministration.Contracts.ITrip;
import Models.Vehicles.Contracts.IVehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Engine implements IEngine {

    private static IEngine instanceHolder = new Engine();

    private final File file;
    private IReader reader;
    private IWriter writer;

    private ITransportIncFactory factory;

    private List<IVehicle> vehicles;
    private List<ITrip> trips;
    private List<ITicket> tickets;

    // private because of Singleton design pattern
    private Engine() {
        this.file = new File("src/InputFiles/InputCommands.txt");
        this.reader = new FileReader(file);
        this.writer = new ConsoleWriter();

        this.factory = new TransportIncFactory();

        this.vehicles = new ArrayList<IVehicle>();
        this.trips = new ArrayList<ITrip>();
        this.tickets = new ArrayList<ITicket>();
    }

    public static IEngine getInstance() {
        return instanceHolder;
    }

    @Override
    public void start() {
        List<String> commands = reader.readFile(file.getName());
        for (int i = 0, sz = commands.size(); i < sz; i++) {
            String command = commands.get(i);
            String[] commandParts = ParseCommand(command);
            ExecuteCommand(commandParts);
        }
    }

    private void ExecuteCommand(String[] commandParts) {
        String commandName = commandParts[0];

        switch (commandName) {
            case "createbus": {
                createBusCommand(commandParts);
                break;
            }
            case "createtrain": {
                createTrainCommand(commandParts);
                break;
            }
            case "createairplane": {
                createAirplaneCommand(commandParts);
                break;
            }

            case "createtrip": {
                createTripCommand(commandParts);
                break;
            }
            case "createticket": {
                createTicketCommand(commandParts);
                break;
            }

            case "listvehicles": {
                listVehiclesCommand();
                break;
            }
            case "listtrips": {
                listTripsCommand();
                break;
            }
            case "listtickets": {
                listTicketsCommand();
                break;
            }
        }
    }

    String[] ParseCommand(String command) {
        String[] commandParts = command.split(" ");
        return commandParts;
    }

    private void createBusCommand(String[] commandParts) {
        double pricePerKilometer = Double.parseDouble(commandParts[2]);
        int passengerCapacity = Integer.parseInt(commandParts[1]);
        IVehicle bus = factory.createBus(pricePerKilometer, passengerCapacity);
        vehicles.add(bus);
    }

    private void createTrainCommand(String[] commandParts) {
        double pricePerKilometer = Double.parseDouble(commandParts[2]);
        int passengerCapacity = Integer.parseInt(commandParts[1]);
        int carts = Integer.parseInt(commandParts[3]);
        IVehicle train = factory.createTrain(pricePerKilometer, passengerCapacity, carts);
        vehicles.add(train);
    }

    private void createAirplaneCommand(String[] commandParts) {
        double pricePerKilometer = Double.parseDouble(commandParts[2]);
        int passengerCapacity = Integer.parseInt(commandParts[1]);
        boolean hasFreeFood = Boolean.parseBoolean(commandParts[3]);
        IVehicle airplane = factory.createAirplane(pricePerKilometer, passengerCapacity, hasFreeFood);
        vehicles.add(airplane);
    }

    private void createTripCommand(String[] commandParts) {
        String startingPoint = commandParts[1];
        String destination = commandParts[2];
        int distance = Integer.parseInt(commandParts[3]);
        int vehicleIndex = Integer.parseInt(commandParts[4]);
        IVehicle vehicle = vehicles.get(vehicleIndex);
        ITrip trip = factory.createTrip(startingPoint, destination, distance, vehicle);
        trips.add(trip);
    }

    private void createTicketCommand(String[] commandParts) {
        int tripIndex = Integer.parseInt(commandParts[1]);
        ITrip trip = trips.get(tripIndex);
        double price = Double.parseDouble(commandParts[2]);
        ITicket ticket = factory.createTicket(trip, price);
        tickets.add(ticket);
    }

    private void listVehiclesCommand() {
        for (int i = 0, sz = vehicles.size(); i < sz; i++) {
            writer.writeLine(vehicles.get(i).toString());
        }
    }

    private void listTripsCommand() {
        for (int i = 0, sz = trips.size(); i < sz; i++) {
            writer.writeLine(trips.get(i).toString());
        }
    }

    private void listTicketsCommand() {
        for (int i = 0, sz = tickets.size(); i < sz; i++) {
            writer.writeLine(tickets.get(i).toString());
        }
    }
}
