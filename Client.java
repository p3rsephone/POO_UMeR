import java.awt.geom.Point2D;
import java.util.stream.Collectors;
import java.util.*;
/**
 * Client that uses UMeR
 *
 * @author (your name)
 * @version 29.04.17
 */
public class Client extends Human {
    // instance variables
    private Point2D.Double place;
	private ArrayList<Trip> trips;


    /**
     * Constructors
     */
    public Client(String mail, String name, String pass, String addr, String birth) {
        super(mail, name, pass, addr, birth);
		this.place = new Point2D.Double();
		this.trips = new ArrayList<Trip>();
    }

	public Client(Point2D.Double place, ArrayList<Trip> trips, String mail, String name, String pass, String addr, String birth) {
		super(mail, name, pass, addr, birth);
		this.place = place;
		this.trips = trips;
    }

	public Client(Client c) {
		super(c);
        this.place = c.getPlace();
		this.trips = c.getTrip();
    }

	/**
     * Getters
     */

	public Point2D.Double getPlace() {
		return this.place;
	}

	public ArrayList<Trip> getTrip() {
		ArrayList<Trip> trip = new ArrayList<Trip>();
        for(Trip t : this.trips) {
            trip.add(t.clone());
		}
		return trip;
	}

	/**
     * Clone method for Client
     *
     * @return     A copy of the Client
     */
	public Client clone () {
		return new Client (this);
	}
}
