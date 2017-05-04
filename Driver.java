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
	private double grading;
	private boolean availability;
	private double timeCompliance;
	private int numberOfReviews;

	/**
	 * Constrói um novo user a partir dos diferentes parametros fornecidos
	 * @param email Email
	 * @param address 	Morada
	 * @param name 	Nome
	 * @param birthday Dia de nascimento
	 * @param password	Password
	 * @param position Posição
	 * @param grading Classificação do motorista
	 * @param availability Disponibilidade
	 * @param mileage Kilometragem
	 * @param timeCompliance Grau de cumprimento de horário
	 * @param numberOfReviews Número de avaliações
	 * @param trips Viagens feitas
	 */
    public Driver(String email, String name, String password, String address, LocalDate birthday, Point2D.Double position, double totalDistance, double grading, Boolean availability, double timeCompliance, int numberOfReviews, ArrayList<Trip> trips, int numberOfTrips){
		super(email, name, password, address, birthday, position, totalDistance, trips, numberOfTrips);
		this.grading = grading;
		this.availability = availability;
		this.timeCompliance = timeCompliance;
		this.numberOfReviews = numberOfReviews;

    }

	/**
	* Constroi um motorista a partir de um já definido
	* @param d
	*/
	public Driver(Driver d) {
		super(d.getEmail(), d.getName(), d.getPassword(), d.getAddress(), d.getBirthday(), d.getPosition(), d.getTotalDistance(), d.getTrips(), d.getNumberOfTrips());
		this.grading = d.getGrading();
		this.availability = d.getAvailability();
		this.timeCompliance = d.getTimeCompliance();
		this.numberOfReviews = d.getNumberOfReviews();
	}

	public Driver(){
		super();
		this.grading = 0;
		this.availability = true;
		this.timeCompliance = 0;
		this.numberOfReviews = 0;
	}


	/**
	 * Retorna a classificação do motorista
	 * @return Classificação
	 */
	public Double getGrading() {
		return this.grading;
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
		return this.timeCompliance;
	}

	/**
	 * Retorna o número de classificações deste
	 * @return
	 */
	public int getNumberOfReviews(){
		return this.numberOfReviews;
	}

	/**
	 * Altera a classificação de um driver
	 * @param grading
	 */
	public void setGrading(double grading){
		this.grading = grading;
	}

	/**
	 * Altera a disponibilidade de um driver
	 * @param b
	 */
	public void setAvailability(Boolean b) {
		this.availability = b;
	}

	/**
	 * Altera o grau de comprimento de um motorista
	 * @param timeCompliance
	 */
	public void setTimeCompliance(double timeCompliance){
		this.timeCompliance = timeCompliance;
	}

	/**
	 * Altera o número de classificações de um driver
	 * @param numberOfReviews
	 */
	public void setNumberOfReviews(int numberOfReviews){
		this.numberOfReviews = numberOfReviews;
	}

	/**
	 * Retorna uma cópia de um motorista
	 * @return Cópia do motorista
	 */
	public Driver clone() {
		return new Driver(this);
	}

	public String toString(){
		return "Driver \n" + super.toString() + "\n" +
				"Disponibilidade : " + this.availability + "\n" +
				"Classificação : " + this.grading + "\n" +
				"Grau de cumprimento : " + this.timeCompliance + "\n" +
				"Número de classificações : " + this.numberOfReviews;
	}

	/**
	 * Verifica se dois condutores são iguais
	 * @param d Condutor a ser comparado
	 * @return São iguais (true) ou não (false)
	 */
	public boolean equals(Driver d){
		return this.getEmail().equals(d.getEmail());
	}

	public void addTrip(Trip t){
		super.addTrip(t);

		if (this.grading != -1) {
			this.grading = (this.grading * numberOfReviews + t.getRating()) / (numberOfReviews + 1);
			this.numberOfReviews++;
		}

	}

}
