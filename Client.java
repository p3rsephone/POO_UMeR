import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.ArrayList;
/**
 * Client that uses UMeR.
 *
 * @author (your name)
 * @version 02.05.17
 */
public class Client extends User {

	/** Variáveis de Instância */
	private double rigor;
	private int mood;
	private int points;

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
    public Client(String email, String name, String password, String address, LocalDate birthday, Point2D.Double position, double totalDistance, ArrayList<Trip> trips, int numberOfTrips, double rigor, int mood, int points) {
        super(email, name, password, address, birthday, position, totalDistance, trips, numberOfTrips);
    }

	/**
	* Constroi um cliente a partir de um já definido
	* @param c
	*/
	public Client(Client c) {
		super(c.getEmail(), c.getName(), c.getPassword(), c.getAddress(), c.getBirthday(), c.getPosition(), c.getTotalDistance(), c.getTrips(), c.getNumberOfTrips());
    }

    /** Metodos de instância */

	/**
	 * Retorna a cópia de um cliente
	 * @return Cópia do cliente
	 */
	public Client clone () {
		return new Client (this);
	}

	public String toString(){
		return super.toString() + "\n" +
				"Rigor : " + this.rigor + "\n" +
				"Mood : " + this.mood + "\n" +
				"Pontos : " + this.points;
	}

	public String requestClosestTaxi(Map<String, Driver> drivers){
		int min = -1;
		String closestDriver = null;
		for (Driver d : drivers.values())
			if (d.getAvailability() == true)
				if (d.getPosition().distance(this.getPosition()) < min)
					closestDriver = d.getEmail();

		return closestDriver;
	}

	public boolean requestSpecificDriver(String mail, Map<String, Driver> drivers){
		if (drivers.get(mail).getAvailability() == true)
			return true;
		else return false;
	}

	public void addTrip(Trip t){
		super.addTrip(t);
		this.points += t.distance() / 4;
	}
}
