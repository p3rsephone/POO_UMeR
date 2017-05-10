import java.awt.geom.Point2D;
import java.time.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    private double time;
    private double price;
    private LocalDate date;
    private String registration;
    private User driver, client;
    private int rating;


    /** Constructores */
    /**
     * Constroi uma viagem sem parametros
     */
    public Trip () {
        this.id = 0;
        this.start = new Point2D.Double(0, 0);
        this.end = new Point2D.Double(0, 0);
        this.time = 0.0;
        this.price = 0.0;
        this.registration = null;
        this.date =  LocalDate.MIN;
        this.driver = null;
        this.client = null;
        this.rating = 0;
    }

    /**
     * Constroi uma viagem a partir de vários parametros
     * @param id            ID da viagem
     * @param start         Posição do início da viagem
     * @param end           Posição do fim da viagem
     * @param time          Tempo da viagem
     * @param price         Preço da viagem
     * @param registration      Matrícula do carro
     * @param driver        Condutor
     * @param client        Cliente
     * @param rating        Classificação dada ao condutor pelo cliente
     */
    public Trip (int id, Point2D.Double start, Point2D.Double end, Double time, Double price, LocalDate date, String registration, User driver, User client, int rating) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.time = time;
        this.price = price;
        this.registration = registration;
        this.date = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        this.driver = driver;
        this.client = client;
        this.rating = rating;
    }

    /**
     * Constroí uma viagem a partir de uma já definida
     * @param t Viagem
     */
    public Trip (Trip t) {
        this(t.getID(), t.getStart(), t.getEnd(), t.getTime(), t.getPrice(), t.getDate(), t.getregistration(), t.getDriver(), t.getClient(), t.getRating());
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

    /**
     * Retorna a data da viagem
     * @return Data da viagem
     */
    public LocalDate getDate(){
        return LocalDate.of(this.date.getYear(), this.date.getMonth(), this.date.getDayOfMonth());
    }

    /**
     * Retorna a matricula do carro onde a viagem foi realizada
     * @return Matrícula
     */
    public String getregistration() {
        return this.registration;
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
     * Retorna a classificação dada ao condutor pelo cliente
     * @return Classificação
     */
    public int getRating(){
        return this.rating;
    }

    /**
     * Faz uma cópia da viagem
     * @return Cópia da viagem
     */
    public Trip clone () {
        return new Trip (this);
    }

    /**
     * Imprime a informação sobre uma viagem
     * @return String com a informação
     */
    public String toString(){
        return "Viagem de " + "("+ this.start.getX() + "," + this.start.getY() + ") ---> (" + this.end.getX() + "," + this.end.getY() + ")" + "\n" +
                "Data : " + this.date + "\n" +
                "Distância : " + distance() + "km\n" +
                "Duração : " + (int) this.time + "h:" + Math.round(this.time * 60)%60 + "m:" + Math.round(this.time * 3600)%60 + "s\n" +
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
