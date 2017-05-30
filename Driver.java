import java.awt.geom.Point2D;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.*;

/**
 * Driver of UMeR.
 *
 * @author (your name)
 * @version 02.05.17
 */
public class Driver extends User implements Serializable {

    /* Variáveis de Instância */
    private double rating;
    private boolean availability;
    private double timeCompliance;
    private int numberOfReviews;
    private int exp;
    private String vehicle;
    private String company;
    private double deviation;


    /**
     * Cria um condutor a partir de todos os parámetros
     * @param email             Email
     * @param name              Morada
     * @param password          Password
     * @param address           Morada
     * @param birthday          Data de nascimento
     * @param totalDistance     Distância total percorrida
     * @param rating           Classificação
     * @param availability      Disponibilidade
     * @param timeCompliance    Fator de cumprimento de hórario
     * @param numberOfReviews   Número de classificações
     * @param trips             Viagens feitas
     * @param money             Dinheiro ganho
     * @param vehicle           Matrícula do seu veículo
     * @param company           Empresa para a qual trabalha
     * @param deviation            Soma dos deviations totais entre tempo de viagem estimado e tempo real
     */
    public Driver(String email, String name, String password, String address, LocalDate birthday, double totalDistance, double rating, Boolean availability, double timeCompliance, ArrayList<Trip> trips, int numberOfTrips, double money, double exp, String vehicle, String company, double deviation){
        super(email, name, password, address, birthday, totalDistance, trips, money);
        this.rating = rating;
        this.availability = availability;
        this.timeCompliance = timeCompliance;
        this.numberOfReviews = numberOfReviews;
        this.vehicle = vehicle;
        this.company = company;
        this.deviation = deviation;
    }

    /**
     * Cria um novo condutor a partir de parametros de registo
     * @param email     Email
     * @param name      Name
     * @param password  Password
     * @param address   Morada
     * @param birthday  Aniversário
     * @param company   Empresa para a qual trabalha
     */
    public Driver(String email, String name, String password, String address, LocalDate birthday, double timeCompliance, String company){
        super(email, name, password, address, birthday, 0, null, 0);
        this.rating = 0;
        this.availability = true;
        this.timeCompliance = timeCompliance;
        this.numberOfReviews = 0;
        this.company = company;
        this.deviation = 0;
    }

    /**
    * Constroi um motorista a partir de um já definido
    * @param d Motorista
    */
    public Driver(Driver d) {
        super(d.getEmail(), d.getName(), d.getPassword(), d.getAddress(), d.getBirthday(), d.getTotalDistance(), d.getTrips(), d.getMoney());
        this.rating = d.getRating();
        this.availability = d.isAvailable();
        this.timeCompliance = d.getTimeCompliance();
        this.numberOfReviews = d.getNumberOfReviews();
        this.exp = d.getExp();
        this.vehicle = d.getVehicle();
        this.company = d.getCompany();
        this.deviation = d.getDeviation();
    }

    /**
     * Constroi um motorista a partir de um construtor vazio
     */
    public Driver(){
        super();
        this.rating = 0;
        this.availability = true;
        this.timeCompliance = 0;
        this.numberOfReviews = 0;
        this.deviation = 0;
    }


    /**
     * Retorna a classificação do motorista
     * @return Classificação
     */
    public Double getRating() {
        return this.rating;
    }

    /**
     * Retorna a disponibilidade do motorista
     * @return Disponibilidade
     */
    public Boolean isAvailable() {
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
     * @return Número de classificações
     */
    public int getNumberOfReviews(){
        return this.numberOfReviews;
    }

    /**
     * Retorna a experiência do condutor
     * @return Experiência
     */
    public int getExp(){
        return this.exp;
    }

    /**
     * Retorna a matrícula do seu veículo (se for condutor privado)
     * @return Matrícula
     */
    public String getVehicle(){
        return this.vehicle;
    }

    /**
     * Retorna o nome da empresa para quem trabalha (null se for condutor privado)
     * @return Nome da empresa
     */
    public String getCompany(){
        return this.company;
    }

    /**
     * Retorna a soma dos deviations realizados pelo condutor
     * @return deviations
     */
    public double getDeviation(){
        return this.deviation;
    }
    
    /**
     * Altera a classificação de um driver
     * @param rating Nova classificação
     */
    public void setRating(double rating){
        this.rating = rating;
    }

    /**
     * Altera a disponibilidade de um driver
     * @param b Nova disponibilidade
     */
    public void setAvailability(Boolean b) {
        this.availability = b;
    }

    /**
     * Altera o grau de comprimento de um motorista
     * @param timeCompliance Novo grau de cumprimento
     */
    public void setTimeCompliance(double timeCompliance){
        this.timeCompliance = timeCompliance;
    }

    /**
     * Altera o número de classificações de um driver
     * @param numberOfReviews Novo número de classificações
     */
    public void setNumberOfReviews(int numberOfReviews){
        this.numberOfReviews = numberOfReviews;
    }

    /**
     * Altera a quantidade de experiencia
     * @param exp Nova quantia
     */
    public void setExp(int exp){
        this.exp = exp;
    }

    /**
     * Altera a matrícula do veículo do driver
     * @param vehicle Nova matrícula
     */
    public void setVehicle(String vehicle){
        this.vehicle = vehicle;
    }
    
    /**
     * Altera os deviations totais de um condutor
     * @param deviation Novo valor de deviation
     */
    public void setdeviation(double deviation){
        this.deviation = deviation;
    }
    
    /**
     * Retorna uma cópia de um motorista
     * @return Cópia do motorista
     */
    public Driver clone() {
        return new Driver(this);
    }

    /**
     * Imprime a informação de um condutor
     * @return String com a informação
     */
    public String toString(){
        return "Driver \n" + super.toString() + "\n" +
                "Disponibilidade : " + this.availability + "\n" +
                "Classificação : " + this.rating + "\n" +
                "Grau de cumprimento : " + this.timeCompliance + "\n" +
                "Número de classificações : " + this.numberOfReviews + "\n" +
                "Veículo : " + this.vehicle + "\n" +
                "Desvio total : " + this.deviation + "\n";
    }

    /**
     * Verifica se dois condutores são iguais
     * @param d Condutor a ser comparado
     * @return São iguais (true) ou não (false)
     */
    public boolean equals(Driver d){
        return this.getEmail().equals(d.getEmail());
    }

    /**
     * Adiciona uma nova viagem a um condutor
     * @param t Viagem a ser adicionada
     */
    public void addTrip(Trip t){
        super.addTrip(t);
        this.setTotalDistance(this.getTotalDistance() + t.getTaxiPos().distance(t.getStart()));
        this.exp += (t.distance()+1)/2;
        this.deviation += Math.abs(t.getPrice() - t.getEstimatedPrice());
    }

    /**
     * Adiciona uma classificação ao condutor
     * @param rating Classificação
     */
    public void addRating(int rating){
        this.rating = (this.rating * numberOfReviews + rating * 20) / (numberOfReviews + 1);
        this.numberOfReviews++;
        if (rating > 2)
            this.exp += 3 * (rating/5);
        else this.exp -= 4-rating;
    }
}
