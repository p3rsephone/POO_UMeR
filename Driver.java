import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.*;
/**
 * Driver of UMeR.
 *
 * @author (your name)
 * @version 02.05.17
 */
public class Driver extends User {

	/* Variáveis de Instância */
    private ArrayList<Trip> trips;
	private double grading;
	private double mileage;
	private boolean availability;
	private double timeCompliance;
	private int reviews;

	/**
	 * Constrói um novo user a partir dos diferentes parametros fornecidos
	 * @param reviews Número de avaliações
	 * @param grading Classificação do motorista
	 * @param mileage Kilometragem
	 * @param availability Disponibilidade
	 * @param timeCompliance Grau de cumprimento de horário
	 * @param trips Viagens feitas
	 * @param email Email
	 * @param name 	Nome
	 * @param password	Password
	 * @param address 	Morada
	 * @param birthday Dia de nascimento
	 * @param position Posição
	 */
    public Driver(int reviews, Double grading, Double mileage, Boolean availability, Double timeCompliance, ArrayList<Trip> trips, String email, String name, String password, String address, LocalDate birthday, Point2D.Double position){
		super(email, name, password, address, birthday, position);
		if (trips != null)
			this.trips = new ArrayList<>(trips);
		else this.trips = new ArrayList<>();
		this.grading = grading;
		this.mileage = mileage;
		this.availability = availability;
		this.timeCompliance = timeCompliance;
		this.reviews = reviews;

    }

	/**
	* Constroi um motorista a partir de um já definido
	* @param d
	*/
	public Driver(Driver d) {
		super(d.getEmail(), d.getName(), d.getPassword(), d.getAddress(), d.getBirthday(), d.getPosition());
		if (d.getTrip() != null)
			this.trips = new ArrayList<>(d.getTrip());
		else this.trips = new ArrayList<>();
		this.grading = d.getGrading();
		this.mileage = d.getMileage();
		this.availability = d.getAvailability();
		this.timeCompliance = d.getTimeCompliance();
		this.reviews = d.getReviews();
	}

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
	 * Retorna a classificação do motorista
	 * @return Classificação
	 */
	public Double getGrading() {
		return (this.grading/this.reviews);
	}

	/**
	 * Retorna a quantidade de kilómetros que o motorista já fez
	 * @return Kilometragem
	 */
	public Double getMileage() {
		return this.mileage;
	}

	/**
	 * Retorna a disponibilidade do motorista
	 * @return Disponibilidade
	 */
	public Boolean getAvailability() {
		return this.availability;
	}

	/**
	 * Retorna o grau de cumprimento de horário do motorista
	 * @return Grau de cumprimento de horário
	 */
	public Double getTimeCompliance() {
		return (this.timeCompliance/this.reviews);
	}

	/**
	 * Retorna o numero de avaliações que o motorista tem
	 * @return Numero de avaliações
	 */
	public int getReviews() {
		return this.reviews;
	}

	/**
	 * Adiciona viagens a um motorista
	 * @param trips Novas viagens
	 */
	public void addTrips(ArrayList<Trip> trips){
		this.trips = new ArrayList<Trip>();
		for (Trip t: trips)
			this.trips.add(t.clone());
	}

	/**
	 * Adiciona uma classificação a um motorista
	 * @param grade Classificação a adicionar
	 */
	public void addGrading(Float grade) {
		this.grading += grade;
		this.reviews +=1;
	}

	/**
	 * Aumenta a kilometragem de um motorista
	 * @param miles Kilometragem a adicionar
	 */
	public void addMileage(Double miles) {
		this.mileage += miles;
	}

	/**
	 * Muda o estado de disponibilidade de um motorista
	 * @param b Estado
	 */
	public void changeAvailability(Boolean b) {
		this.availability = b;
	}

	/**
	 * Adiciona uma classificação do grau de cumprimento horário a um motorista
	 * @param time Grau de cumprimento a adicionar
	 */
	public void addTimeReview(Float time) {
		this.timeCompliance += time;
		this.reviews +=1;
	}

	//TODO: toString

	/**
	 * Retorna uma cópia de um motorista
	 * @return Cópia do motorista
	 */
	public Driver clone() {
		return new Driver(this);
	}

}
