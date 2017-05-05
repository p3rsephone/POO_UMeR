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
    public Client(String email, String name, String password, String address, LocalDate birthday, Point2D.Double position, double totalDistance, ArrayList<Trip> trips, int numberOfTrips, double rigor, int points) {
        super(email, name, password, address, birthday, position, totalDistance, trips, numberOfTrips);
        this.rigor = rigor;
        this.points = points;
    }

	/**
	* Constroi um cliente a partir de um já definido
	* @param c
	*/
	public Client(Client c) {
		super(c.getEmail(), c.getName(), c.getPassword(), c.getAddress(), c.getBirthday(), c.getPosition(), c.getTotalDistance(), c.getTrips(), c.getNumberOfTrips());
		this.rigor = c.getRigor();
		this.points = c.getPoints();
    }

	/**
	 * Constroi um cliente sem parámetros
	 */
	public Client(){
		super();
		this.rigor = 0;
		this.points = 0;
	}

    /** Metodos de instância */

	/**
	 * Retorna o grau de rigor de um cliente
	 * @return Grau de rigor
	 */
	public double getRigor() {
		return rigor;
	}

	/**
	 * Retorna o número de pontos de um cliente
	 * @return Número de pontos
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Altera o grau de rigor de um cliente
	 * @param rigor Novo grau de rigor
	 */
	public void setRigor(double rigor) {
		this.rigor = rigor;
	}

	/**
	 * Altera o número de pontos de um cliente
	 * @param points Novo número de pontos
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Retorna a cópia de um cliente
	 * @return Cópia do cliente
	 */
	public Client clone () {
		return new Client (this);
	}

	/**
	 * Imprime a informação de um cliente
	 * @return String com a informação
	 */
	public String toString(){
		return super.toString() + "\n" +
				"Rigor : " + this.rigor + "\n" +
				"Pontos : " + this.points;
	}

	/**
	 * Faz um pedido para o taxi mais próximo disponível
	 * @param drivers Map com todos os condutores
	 * @return Driver mais próximo (null se estiverem todos ocupados)
	 */
	public String requestClosestTaxi(Map<String, Driver> drivers){
		int min = -1;
		String closestDriver = null;
		for (Driver d : drivers.values())
			if (d.getAvailability() == true)
				if (d.getPosition().distance(this.getPosition()) < min)
					closestDriver = d.getEmail();

		return closestDriver;
	}

	/**
	 * Faz um pedido para um condutor específico
	 * @param email Email do condutor pretendido
	 * @param drivers Map com todos os condutores
	 * @return O condutor específico encontra-se disponível (true) ou não (false)
	 */
	public boolean requestSpecificDriver(String email, Map<String, Driver> drivers){
		if (drivers.get(email).getAvailability() == true)
			return true;
		else return false;
	}

	/**
	 * Adiciona uma nova viagem ao cliente
	 * @param t Viagem a adicinar
	 */
	public void addTrip(Trip t){
		super.addTrip(t);
		this.points += t.getPrice() / 4;
	}
}
