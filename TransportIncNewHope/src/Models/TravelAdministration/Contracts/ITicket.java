package Models.TravelAdministration.Contracts;

public interface ITicket {
    double getPrice();

    ITrip getTrip ();

    double calculatePrice();
}
