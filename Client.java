import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.*;
/**
 * Client that uses UMeR.
 *
 * @author (your name)
 * @version 02.05.17
 */
public class Client extends User {

	/** Variáveis de Instância */
	private ArrayList<Trip> trips;


	/**
	 * Constrói um novo cliente a partir dos diferentes parametros fornecidos
	 * @param trips Viagens feitas
	 * @param email Email
	 * @param name 	Nome
	 * @param pass	Password
	 * @param addr 	Morada
	 * @param birth Dia de nascimento
	 * @param position Posição
	 */
    public Client(ArrayList<Trip> trips, String email, String name, String password, String address, LocalDate birthday, Point2D.Double position) {
        super(email, name, password, address, birthday, position);
		if (trips != null)
			this.trips = new ArrayList<>(trips);
		else this.trips = new ArrayList<>();
    }

	/**
	* Constroi um cliente a partir de um já definido
	* @param c
	*/
	public Client(Client c) {
		super(c.getEmail(), c.getName(), c.getPassword(), c.getAddress(), c.getBirthday(), c.getPosition());
		if (c.getTrip() != null)
			this.trips = new ArrayList<>(c.getTrip());
		else this.trips = new ArrayList<>();
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
	 * Adiciona viagens a um utilizador
	 * @param trips Novas viagens
	 */
	public void addTrips(ArrayList<Trip> trips){
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
