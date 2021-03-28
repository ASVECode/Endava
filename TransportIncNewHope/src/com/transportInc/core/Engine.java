package com.transportInc.core;

import com.transportInc.comparators.*;

import java.sql.*;

import com.transportInc.core.factories.ITransportIncFactory;
import com.transportInc.core.factories.TransportIncFactory;
import com.transportInc.core.providers.ConsoleWriter;
import com.transportInc.core.providers.FileReader;
import com.transportInc.database.Connector;
import com.transportInc.models.travel_administration.contracts.ITicket;
import com.transportInc.models.travel_administration.contracts.ITrip;
import com.transportInc.models.vehicles.Vehicle;
import com.transportInc.models.vehicles.contracts.IVehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Engine {

    private static final Engine instanceHolder = new Engine();

    private final File file;
    private FileReader reader;
    private ConsoleWriter writer;

    private ITransportIncFactory factory;

    private List<IVehicle> vehicles;
    private List<ITrip> trips;
    private List<ITicket> tickets;

    // private because of Singleton design pattern
    private Engine() {
        this.file = new File("src/com/transportInc/input_files/input_commands.txt");
        this.reader = new FileReader(file);
        this.writer = new ConsoleWriter();

        this.factory = new TransportIncFactory();

        this.vehicles = new ArrayList<IVehicle>();
        this.trips = new ArrayList<ITrip>();
        this.tickets = new ArrayList<ITicket>();
    }

    public static Engine getInstance() {
        return instanceHolder;
    }


    public void start() {
        List<String> commands = reader.readFile(file.getName());
        for (int i = 0, sz = commands.size(); i < sz; i++) {
            String command = commands.get(i);
            String[] commandParts = parseCommand(command);
            executeCommand(commandParts);
        }

        Connection connection = Connector.getConnector().getConnection();

        try {
            String queryInsertVehicles = "INSERT INTO vehicles(name, type, price_per_kilometer, passenger_capacity)" + "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(queryInsertVehicles);
            for (int i = 0, sz = vehicles.size(); i < sz; i++) {
                preparedStatement.setString(1, ((Vehicle) vehicles.get(i)).getVehicleName().name());
                preparedStatement.setString(2, ((Vehicle) vehicles.get(i)).getVehicleType().name());
                preparedStatement.setDouble(3, ((Vehicle) vehicles.get(i)).getPricePerKilometer());
                preparedStatement.setInt(4, ((Vehicle) vehicles.get(i)).getPassengerCapacity());
                preparedStatement.executeUpdate();
            }

            String queryInsertTrips = "INSERT INTO trips(starting_point, destination, distance, vehicle_name)" + "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryInsertTrips);

            for (int i = 0, sz = trips.size(); i < sz; i++) {
                preparedStatement.setString(1, (trips.get(i)).getStartPoint());
                preparedStatement.setString(2, (trips.get(i)).getDestination());
                preparedStatement.setInt(3, (trips.get(i)).getDistance());
                preparedStatement.setString(4, (trips.get(i)).getVehicle().getVehicleName().name());
                preparedStatement.executeUpdate();
            }

            String queryInsertTickets = "INSERT INTO tickets(trip_destination, price)" + "VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(queryInsertTickets);

            for (int i = 0, sz = tickets.size(); i < sz; i++) {
                preparedStatement.setString(1, (tickets.get(i)).getTrip().getDestination());
                preparedStatement.setDouble(2, (tickets.get(i)).getPrice());
                preparedStatement.executeUpdate();
            }
            connection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    private void executeCommand(String[] commandParts) {
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

            case "sortvehiclebypassengercapacity": {
                writer.writeLine("Sorting by passenger capacity");
                writer.writeLine("-----------------------------");
                sortByVehiclePassengerCapacityCommand();
                break;
            }

            case "sortvehiclesbytype": {
                writer.writeLine("Sorting by type");
                writer.writeLine("---------------");
                sortByVehicleTypeCommand();
                break;
            }

            case "sortvehiclebypriceperkilometer": {
                writer.writeLine("Sorting by price per kilometer");
                writer.writeLine("------------------------------");
                sortByVehiclePricePerKilometerCommand();
                break;
            }

            case "sortticketsbydestination": {
                writer.writeLine("Sorting tickets by destination");
                writer.writeLine("------------------------------");
                sortByTicketDestinationCommand();
                break;
            }

            case "sortticketbyprice": {
                writer.writeLine("Sorting tickets by price");
                writer.writeLine("------------------------");
                sortByTicketPriceCommand();
                break;
            }

            case "sorttripbydistance": {
                writer.writeLine("Sorting trips by distance");
                writer.writeLine("-------------------------");
                sortByTripDistanceCommand();
                break;
            }

            case "sorttripbystartingpoint": {
                writer.writeLine("Sorting trips by starting point");
                writer.writeLine("------------------------");
                sortByTripStartingPointCommand();
                break;
            }

            case "sorttripbydestination": {
                writer.writeLine("Sorting trips by destination");
                writer.writeLine("----------------------------");
                sortByTripDestinationCommand();
                break;
            }

        }
    }


    String[] parseCommand(String command) {
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
        ITicket ticket = factory.createTicket(trip, price, tripIndex);
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

    private void sortByVehiclePassengerCapacityCommand() {
        Collections.sort(this.vehicles, new VehiclePassengerCapacityComparator());
    }

    private void sortByVehiclePricePerKilometerCommand() {
        Collections.sort(this.vehicles, new VehiclePricePerKilometerComparator());
    }

    private void sortByVehicleTypeCommand() {
        Collections.sort(this.vehicles, new VehicleTypeComparator());
    }

    private void sortByTicketDestinationCommand() {
        Collections.sort(this.tickets, new TicketDestinationComparator());
    }

    private void sortByTicketPriceCommand() {
        Collections.sort(this.tickets, new TicketPriceComparator());
    }

    private void sortByTripDistanceCommand() {
        Collections.sort(this.trips, new TripDistanceComparator());
    }

    private void sortByTripDestinationCommand() {
        Collections.sort(this.trips, new TripDestinationComparator());
    }

    private void sortByTripStartingPointCommand() {
        Collections.sort(this.trips, new TripStartPointComparator());
    }

}
