import java.awt.geom.Point2D;
import java.util.stream.Collectors;
import java.util.*;
/**
 * Driver of UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver extends User {
    // instance variables
    private ArrayList<Trip> trips;
	private float grading;
	private double mileage;
	private boolean availability;
	private float timeCompliance;
	private int reviews;

    /**
     * Constructor for objects of class Driver
     */
    public Driver(int reviews, Float grading, Double mileage, Boolean availability, Float timeCompliance, ArrayList<Trip> trips, String email, String name, String password, String address, Date birthday,Point2D.Float position){
		super(email, name, password, address, birthday, position);
		this.trips = new ArrayList<Trip>(trips);
		this.grading = grading;
		this.mileage = mileage;
		this.availability = availability;
		this.timeCompliance = timeCompliance;
		this.reviews = reviews;

    }

	public Driver(Driver d) {
		super(d.getEmail(), d.getName(), d.getPassword(), d.getAddress(), d.getBirthday(), d.getPosition());
		this.trips = new ArrayList<Trip>(d.getTrip());
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
	public Float getGrading() {
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
	public Float getTimeCompliance() {
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
