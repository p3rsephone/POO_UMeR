import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * User of UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class User {

    /** Variáveis de Instância */

    private String email;
	private String name;
	private String password;
	private String address;
	private LocalDate birthday;
	private double totalDistance;
	private ArrayList<Trip> trips;
	private int numberOfTrips;


    /** Construtores */

	/**
	 * Constrói um novo user a partir dos diferentes parametros fornecidos
	 * @param email Email
	 * @param name 	Nome
	 * @param pass	Password
	 * @param addr 	Morada
	 * @param birth Dia de nascimento
	 * @param position Posição
	 */
	public User(String email, String name, String password, String address, LocalDate birthday, double totalDistance, ArrayList<Trip> trips, int numberOfTrips){
		this.email = email;
		this.name = name;
		this.password = password;
		this.address = address;
		this.birthday = LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
		this.totalDistance = totalDistance;
		if (trips != null) this.trips = new ArrayList<>(trips);
		else this.trips = new ArrayList<>();
		this.numberOfTrips = numberOfTrips;
	}

	/**
	 * Constroi um user a partir de um já definido
	 * @param p
	 */
	public User(User p){
		this(p.getEmail(), p.getName(), p.getPassword(), p.getAddress(), p.getBirthday(), p.getTotalDistance(), p.getTrips(), p.getNumberOfTrips());
	}

	/**
	 * Constroi um user sem parametros (tudo a null e 0,0)
	 */
	public User(){
		this.email = null;
		this.name = null;
		this.password = null;
		this.address = null;
		this.birthday = LocalDate.of(0,0,0);
		this.trips = new ArrayList<>();
	}


    /** Metodos de Intância */

	/**
	 * Retorna o email de um user
	 * @return email
	 */
	public String getEmail(){
 		return this.email;
 	}

	/**
	 * Retorna o nome de um user
	 * @return name
	 */
 	public String getName(){
 		return this.name;
 	}

	/**
	 * Retorna o password de um user
	 * @return password
	 */
 	public String getPassword(){
 		return this.password;
 	}

	/**
	 * Retorna a morada de um user
	 * @return address
	 */
 	public String getAddress(){
 		return this.address;
 	}

	/**
	 * Retorna a data de aniversário de um user
	 * @return birthday
	 */
 	public LocalDate getBirthday(){
 		return LocalDate.of(this.birthday.getYear(), this.birthday.getMonth(), this.birthday.getDayOfMonth());
 	}

	/**
	 * Retorna o número de kilometros feitos pelo user
	 * @return Número de kilometros total
	 */
	public double getTotalDistance(){
		return this.totalDistance;
	}

	/**
	 * Retorna uma cópia do ArrayList com as viagens
	 * @return Viagens
	 */
	public ArrayList<Trip> getTrips() {
		ArrayList<Trip> trip = new ArrayList<>();
		if (this.trips != null)
			for(Trip t : this.trips) {
				trip.add(t.clone());
			}
		return trip;
	}

	/**
	 * Retorna o número de viagens já efetuadas por um user
	 * @return Número de viagens
	 */
	public int getNumberOfTrips(){
		return this.numberOfTrips;
	}

	/**
	 * Altera o email de um user
	 * @param email Novo email
	 */
	public void setEmail(String email){
 		this.email = email;
	}

	/**
	 * Altera o nome de um user
	 * @param name Novo nome
	 */
	public void setName(String name){
 		this.name = name;
	}

	/**
	 * Altera a password de um user
	 * @param password Nova password
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * Altera a morada de um user
	 * @param address Novo morada
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * Altera o aniversário de um user
	 * @param birthday Novo aniversário
	 */
	public void setBirthday(LocalDate birthday){
		this.birthday = LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
	}

	/**
	 * Altera o número de viagens de um user
	 * @param numberOfTrips Novo número de viagens
	 */
	public void setNumberOfTrips(int numberOfTrips){
		this.numberOfTrips = numberOfTrips;
	}


	/**
	 * Imprime a informação de um utilizador
	 * @return String com a informação
	 */
	public String toString(){
		return 	"Utilizador - " 		+ this.name 		+ "\n" +
				"eMail : " 				+ this.email 		+ "\n" +
				"Password : " 			+ this.password 	+ "\n" +
				"Morada : " 			+ this.address 		+ "\n" +
				"Data de nascimento : " + this.birthday 	+ "\n" +
				"Distância Total : " 	+ this.totalDistance + "\n"+
				"Número de viagens :"	+ this.numberOfTrips;
	}

	/**
	 * Compara dois utilizadores
	 * @param u utilizador
	 * @return É o mesmo utilizador (true) ou não (false)
	 */
	public boolean equals(User user){
		return this.email.equals(user.getEmail());
	}

	/**
	 * Faz a cópia de um user
	 * @return Cópia de User
	 */
	public abstract User clone();
	
	/**
	 * Adiciona uma viagem a um utilizador
	 * @param trips Viagem a ser adicionada
	 */
	public void addTrip(Trip t){
		this.trips.add(t.clone());
		this.totalDistance += t.distance();
		this.numberOfTrips++;
	}

}
