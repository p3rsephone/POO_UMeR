import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.HashMap;
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
	private Point2D.Double position;
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
        super(email, name, password, address, birthday, totalDistance, trips, numberOfTrips);
		this.position = new Point2D.Double(position.getX(), position.getY());
        this.rigor = rigor;
        this.points = points;
    }

	/**
	* Constroi um cliente a partir de um já definido
	* @param c
	*/
	public Client(Client c) {
		super(c.getEmail(), c.getName(), c.getPassword(), c.getAddress(), c.getBirthday(), c.getTotalDistance(), c.getTrips(), c.getNumberOfTrips());
		this.rigor = c.getRigor();
		this.points = c.getPoints();
		this.position = new Point2D.Double(c.getPosition().getX(), c.getPosition().getY());
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
	 * Retorna a posição de um user
	 * @return position
	 */
	public Point2D.Double getPosition(){
		return new Point2D.Double(this.position.getX(), this.position.getY());
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
	 * Altera a posição de um user
	 * @param position Nova posição
	 */
	public void setPosition(Point2D.Double position){
		this.position = position;
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
				"Posição : " + "(" + this.position.getX() + "," + this.position.getY() + ")\n"+
				"Rigor : " + this.rigor + "\n" +
				"Pontos : " + this.points;
	}

	/**
	 * Adiciona uma nova viagem ao cliente
	 * @param t Viagem a adicinar
	 */
	public void addTrip(Trip t){
		super.addTrip(t);
		this.position.setLocation(t.getEnd());
		this.points += t.getPrice() / 4;
	}
}
