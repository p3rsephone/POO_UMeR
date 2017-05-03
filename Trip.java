import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Trip.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Trip {

	/** Variáveis de Instância */

	private int id;
	private Point2D.Double start;
	private Point2D.Double end;
	private Double time;
	private Double price;
	private LocalDateTime date;
	private String carPlate;
	private User driver, client;
	private int rating;


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
    public Trip (int id, Point2D.Double start, Point2D.Double end, Double time, Double price, LocalDateTime date, String carPlate, User driver, User client, int rating) {
        this.id = id;
    	this.start = start;
		this.end = end;
		this.time = time;
		this.price = price;
		this.carPlate = carPlate;
		this.date = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), date.getHour(), date.getMinute(), date.getSecond());
		this.driver = driver;
		this.client = client;
		this.rating = rating;
    }

	/**
	 * Constroí uma viagem a partir de uma já definida
	 * @param t Viagem
	 */
	public Trip (Trip t) {
		this(t.getID(), t.getStart(), t.getEnd(), t.getTime(), t.getPrice(), t.getDate(), t.getCarPlate(), t.getDriver(), t.getClient(), t.getRating());
	}

	/** Metodos de Instância */

	public int getID(){
		return this.id;
	}

	/**
	 * Retorna o início da viagem
	 * @return Início
	 */
	public Point2D.Double getStart() {
		Point2D.Double start = new Point2D.Double();
		start.setLocation(this.start.getX(), this.start.getY());
		return start;
	}

	/**
	 * Retorna o fim de uma viagem
	 * @return Fim
	 */
	public Point2D.Double getEnd() {
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

	public LocalDateTime getDate(){
		return LocalDateTime.of(this.date.getYear(), this.date.getMonth(), this.date.getDayOfMonth(), this.date.getHour(), this.date.getMinute(), this.date.getSecond());
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

	public int getRating(){
		return this.rating;
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
		return "Viagem de " + "("+ this.start.getX() + "," + this.start.getY() + ") ---> (" + this.end.getX() + "," + this.end.getY() + ")" + "\n" +
				"Data : " + this.date + "\n" +
				"Distância : " + distance() + "km\n" +
				"Duração :" + this.time + "\n" +
				"Preço : " + this.price + "€\n" +
				"Email condutor : " + this.driver.getEmail() + "\n" +
				"Email cliente : " + this.client.getEmail() + "\n" +
				"Classificação :" + this.rating;
	}

	/**
	 * Calcula a distância de uma viagem
	 * @return Distância
	 */
	public double distance(){
		return start.distance(end);
	}
}
