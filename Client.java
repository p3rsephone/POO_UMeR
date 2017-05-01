import java.awt.geom.Point2D;
import java.util.stream.Collectors;
import java.util.*;
/**
 * Client that uses UMeR
 *
 * @author (your name)
 * @version 29.04.17
 */
public class Client extends User {
	/** Variáveis de Instância */

	private ArrayList<Trip> trips;


    /** Construtores */

    public Client(ArrayList<Trip> trips, String email, String name, String password, String address, Date birthday, Point2D.Float position) {
        super(email, name, password, address, birthday, position);
		this.trips = new ArrayList<Trip>(trips);
    }

	public Client(Client c) {
		super(c.getEmail(), c.getName(), c.getPassword(), c.getAddress(), c.getBirthday(), c.getPosition());
		this.trips = new ArrayList<Trip>(c.getTrip());
    }

    /** Metodos de instância */

	/**
	 * Retorna uma cópia do ArrayList com as viagens
	 * @return Viagens
	 */
	public ArrayList<Trip> getTrip() {
		ArrayList<Trip> trip = new ArrayList<Trip>();
        for(Trip t : this.trips) {
            trip.add(t.clone());
		}
		return trip;
	}

	/**
	 * Altera as viagens de um utilizador
	 * @param trips Novas viagens
	 */
	public void setTrips(ArrayList<Trip> trips){
		this.trips = new ArrayList<Trip>();
		for (Trip t: trips)
			this.trips.add(t.clone());
	}

	/**
	 * Retorna a cópia de um cliente
	 * @return Cópia do cliente
	 */
	public Client clone () {
		return new Client (this);
	}
}
