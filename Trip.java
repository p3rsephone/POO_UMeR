import java.awt.geom.Point2D;
import java.io.Serializable;
import java.time.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Trip.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Trip implements Serializable {

    /** Variáveis de Instância */

    private int id;
    private Point2D.Double start;
    private Point2D.Double end;
    private double time;
    private double price;
    private LocalDate date;
    private String licencePlate;
    private String driver, client;
    private int rating;
    private double estimatedTimeToDest;
    private Point2D.Double taxiPos;
    private double estimatedTimeToClient;
    private double realTimeToClient;
    private double estimatedPrice;


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
        this.licencePlate = null;
        this.date =  LocalDate.MIN;
        this.driver = null;
        this.client = null;
        this.rating = 0;
        this.estimatedTimeToClient = 0;
        this.estimatedTimeToDest = 0;
        this.realTimeToClient = 0;
        this.estimatedPrice = 0;
    }

    /**
     * Constroi uma viagem a partir de vários parametros
     * @param id                    ID da viagem
     * @param start                 Posição do início da viagem
     * @param end                   Posição do fim da viagem
     * @param time                  Tempo da viagem
     * @param price                 Preço da viagem
     * @param licencePlate          Matrícula do carro
     * @param driver                Condutor
     * @param client                Cliente
     * @param rating                Classificação dada ao condutor pelo cliente
     * @param estimatedTimeToDest   Tempo estimado até ao destino
     * @param taxiPos               Posição incial do taxi
     * @param estimatedTimeToClient Tempo estimado até ao cliente
     * @param realTimeToClient      Tempo real até ao cliente
     * @param estimatedPrice        Preço da estimado da viagem
     */
    public Trip (int id, Point2D.Double start, Point2D.Double end, Double time, Double price, LocalDate date, String licencePlate, String driver, String client, int rating, double estimatedTimeToDest, Point2D.Double taxiPos, double estimatedTimeToClient, double realTimeToClient, double estimatedPrice) {
        this.id = id;
        this.start = new Point2D.Double(start.getX(), start.getY());
        this.end = new Point2D.Double(end.getX(), end.getY());
        this.time = time;
        this.price = price;
        this.licencePlate = licencePlate;
        this.date = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        this.driver = driver;
        this.client = client;
        this.rating = rating;
        this.estimatedTimeToDest = estimatedTimeToDest;
        this.taxiPos = new Point2D.Double(taxiPos.getX(), taxiPos.getY());
        this.estimatedTimeToClient = estimatedTimeToClient;
        this.realTimeToClient = realTimeToClient;
        this.estimatedPrice = estimatedPrice;
    }

    /**
     * Constroí uma viagem a partir de uma já definida
     * @param t Viagem
     */
    public Trip (Trip t) {
        this(t.getID(), t.getStart(), t.getEnd(), t.getTime(),
                t.getPrice(), t.getDate(), t.getLicencePlate(),
                t.getDriver(), t.getClient(), t.getRating(),
                t.getEstimatedTimeToDest(), t.getTaxiPos(),
                t.getEstimatedTimeToClient(), t.getRealTimeToClient(),
                t.getEstimatedPrice());
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
        return new Point2D.Double(this.start.getX(), this.start.getY());
    }

    /**
     * Retorna o fim de uma viagem
     * @return Fim
     */
    public Point2D.Double getEnd() {
        return new Point2D.Double(this.end.getX(), this.end.getY());
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
    public String getLicencePlate() {
        return this.licencePlate;
    }

    /**
     * Retorna o condutor da viagem
     * @return Condutor
     */
    public String getDriver() {
        return this.driver;
    }

    /**
     * Retorna o cliente da viagem
     * @return Cliente
     */
    public String getClient(){
        return this.client;
    }

    /**
     * Retorna a classificação dada ao condutor pelo cliente
     * @return Classificação
     */
    public int getRating(){
        return this.rating;
    }

    /**
     * Retorna o tempo incialmente previsto
     * @return Tempo previsto
     */
    public double getEstimatedTimeToDest(){
        return this.estimatedTimeToDest;
    }

    /**
     * Retorna a posição inicial do taxi
     * @return Posição inicial do taxi
     */
    public Point2D.Double getTaxiPos(){
        return new Point2D.Double(this.taxiPos.getX(), this.taxiPos.getY());
    }

    /**
     * Retorna o tempo estimado do taxi ao cliente
     * @return Tempo estimado até ao cliente
     */
    public double getEstimatedTimeToClient(){
        return this.estimatedTimeToClient;
    }

    /**
     * Retorna o tempo que o taxi demorou a chegar ao cliente
     * @return Tempo até ao cliente
     */
    public double getRealTimeToClient(){
        return this.realTimeToClient;
    }

    /**
     * Retorna o tempo estimado da viagem
     * @return
     */
    public double getEstimatedPrice(){
        return this.estimatedPrice;
    }

    /**
     * Altera a classificação de uma viagem
     * @param rating
     */
    public void setRating(int rating){
        this.rating = rating;
    }

    /**
     * Faz uma cópia da viagem
     * @return Cópia da viagem
     */
    public Trip clone () {
        return new Trip (this);
    }

    /**
     * Imprime uma classificação
     * @return String com a classificação
     */
    private String printRating(){
        if (this.rating == -1)
            return "Não classificado";
        else return Integer.toString(this.rating) + "**";
    }

    /**
     * Imprime o tempo em h:m:s
     * @param time  Tempo
     * @return  String com o tempo em h:m:s
     */
    private String printTime(double time){
        return (int) time + "h:" + Math.round(time * 60)%60 + "m:" + Math.round(time * 3600)%60 + "s";
    }

    /**
     * Imprime o dinehiro em €
     * @param money Dinheiro
     * @return String com o dinheiro em €
     */
    private String printMoney(double money){
        return (int) money + "€";
    }

    /**
     * Imprime a distância em km
     * @param distance Distância
     * @return String com a distância em km
     */
    private String printDistance(double distance){
        return (int) distance + "km";
    }

    /**
     * Imprime a informação sobre uma viagem
     * @return String com a informação
     */
    public String toString(){
        return "Viagem de " + "("+ this.start.getX() + "," + this.start.getY() + ") ---> (" + this.end.getX() + "," + this.end.getY() + ")" +
                "\nData : " + this.date +
                "\nDistância : " + printDistance(this.distance()) +
                "\nDuração prevista : " + printTime(this.estimatedTimeToDest) +
                "\nDuração real : " + printTime(this.time) +
                "\nPreço : " + printMoney(this.price) +
                "\nEmail condutor : " + this.driver +
                "\nEmail cliente : " + this.client +
                "\nMatrícula veículo : " + this.licencePlate +
                "\nClassificação : " + printRating() +
                "\nPosição inicial do taxi : [" + this.taxiPos.getX() + "," + this.taxiPos.getY() + "]" +
                "\nTempo previsto até ao cliente : " + printTime(this.estimatedTimeToClient) +
                "\nTempo real até ao cliente : " + printTime(this.realTimeToClient) +
                "\nPreço estimado : " + printMoney(this.estimatedPrice) +
                "\nTrip id : " + this.id;
    }

    /**
     * Calcula a distância de uma viagem
     * @return Distância
     */
    public double distance(){
        return start.distance(end);
    }
}
