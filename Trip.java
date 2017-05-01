import java.awt.geom.Point2D;
import java.util.Date;

/**
 * Trip.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Trip {

	/** Variáveis de Instância */
	private Point2D.Float start;
	private Point2D.Float end;
	private Double time;
	private Double price;
	private Date date;
	private String carPlate;
	private User driver, client;


    /** Constructores */

	/**
	 * Constroi uma viagem a partir de vários parametros
	 * @param start 		Posição do início da viagem
	 * @param end			Posição do fim da viagem
	 * @param time			Tempo da viagem
	 * @param price			Preço da viagem
	 * @param carPlate		Matrícula do carro
	 * @param driver		Condutor
	 * @param client		Cliente
	 */
    public Trip (Point2D.Float start, Point2D.Float end, Double time, Double price, String carPlate, User driver, User client) {
        this.start = start;
		this.end = end;
		this.time = time;
		this.price = price;
		this.carPlate = carPlate;
		this.driver = driver;
		this.client = client;
    }

	/**
	 * Constroí uma viagem a partir de uma já definida
	 * @param t Viagem
	 */
	public Trip (Trip t) {
		this(t.getStart(), t.getEnd(), t.getTime(), t.getPrice(), t.getCarPlate(), t.getDriver(), t.getClient());
	}

	/** Metodos de Instância */

	/**
	 * Retorna o início da viagem
	 * @return Início
	 */
	public Point2D.Float getStart() {
		Point2D.Float start = new Point2D.Float();
		start.setLocation(this.start.getX(), this.start.getY());
		return start;
	}

	/**
	 * Retorna o fim de uma viagem
	 * @return Fim
	 */
	public Point2D.Float getEnd() {
		return this.end;
	}

	/**
	 * Retorna a duração de uma viagem
	 * @return Tempo
	 */
	public Double getTime() {
		return this.time;
	}

	/**
	 * Retorna o preço de uma viagem
	 * @return Preço
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * Retorna a matricula do carro onde a viagem foi realizada
	 * @return Matrícula
	 */
	public String getCarPlate() {
		return this.carPlate;
	}

	/**
	 * Retorna o condutor da viagem
	 * @return Condutor
	 */
	public User getDriver() {
		return this.driver.clone();
	}

	/**
	 * Retorna o cliente da viagem
	 * @return Cliente
	 */
	public User getClient(){
		return this.client.clone();
	}

	/**
     * Clone method for Trip
     *
     * @return     A copy of the Trip
     */
	public Trip clone () {
		return new Trip (this);
	}

	public String toString(){
		return "Viagem de " + this.start + "->" + this.end + "\n" +
				"Data : " + this.date + "\n" +
				"Duração :" + this.time + "\n" +
				"Preço : " + this.price + "\n" +
				"Email condutor : " + this.driver.getEmail() + "\n" +
				"Email cliente : " + this.client.getEmail();
	}
}
