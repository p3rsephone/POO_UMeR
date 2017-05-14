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
    private int points;
    private boolean premium;
    private String queue;


    /**
     * Cria um cliente a partir de todos os parametros
     * @param email             Email
     * @param name              Nome
     * @param password          Password
     * @param address           Morada
     * @param birthday          Data de nascimento
     * @param position          Posição
     * @param totalDistance     Distância total
     * @param trips             Viagens feitas
     * @param numberOfTrips     Número de viagens
     * @param points            Pontos
     * @param money             Dinehiro gasto
     * @param premium           Se tem conta premium
     * @param queue             Veículo onde está em fila de espera
     */
    public Client(String email, String name, String password, String address, LocalDate birthday, Point2D.Double position, double totalDistance, ArrayList<Trip> trips, int numberOfTrips,  int points, double money, boolean premium, String inQueue) {
        super(email, name, password, address, birthday, totalDistance, trips, numberOfTrips, money);
        this.position = new Point2D.Double(position.getX(), position.getY());
        this.points = points;
        this.position = new Point2D.Double(0,0);
        this.premium = premium;
    }

    /**
     * Cria um cliente a partir de paramtros de registo
     * @param email     Email
     * @param name      Nome
     * @param password  Password
     * @param address   Morada
     * @param birthday  Dia de nascimento
     */
    public Client(String email, String name, String password, String address, LocalDate birthday){
        super(email, name, password, address, birthday, 0, null, 0, 0);
        this.points = 0;
        this.premium = false;
        this.position = new Point2D.Double(0,0);
        this.position = new Point2D.Double(position.getX(), position.getY());
    }

    /**
     * Constroi um cliente a partir de um já definido
     * @param c Cliente
     */
    public Client(Client c) {
        super(c.getEmail(), c.getName(), c.getPassword(), c.getAddress(), c.getBirthday(), c.getTotalDistance(), c.getTrips(), c.getNumberOfTrips(), c.getMoney());
        this.points = c.getPoints();
        this.position = new Point2D.Double(c.getPosition().getX(), c.getPosition().getY());
        this.premium = c.isPremium();
        this.queue = c.getQueue();
    }

    /**
     * Constroi um cliente sem parámetros
     */
    public Client(){
        super();
        this.points = 0;
        this.premium = false;
        this.queue = null;
    }

    /** Metodos de instância */
    
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
     * Altera o número de pontos de um cliente
     * @param points Novo número de pontos
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Verifica se um cliente é premium
     * @return Se é premium (True) ou não (False)
     */
    public boolean isPremium() {
        return this.premium;
    }

    /**
     * Retorna a matrícula do veículo onde está em fila de espera
     * @return Matrícula
     */
    public String getQueue(){
        return this.queue;
    }

    /**
     * Altera a posição de um user
     * @param position Nova posição
     */
    public void setPosition(Point2D.Double position){
        this.position.setLocation(position.getX(), position.getY());
    }

    /**
     * Altera o estado premium de um user
     * @param b Novo estado
     */
    public void setPremium(boolean b) {
        this.premium = b;
    }

    /**
     * Altera o veículo onde o cliente está em fila de espera
     * @param b Nova matrícula
     */
    public void setQueue(String queue){
        this.queue = queue;
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
                "Pontos : " + this.points + "\n" +
                "Premium : " + this.premium + "\n" +
                "Fila de espera : " + this.queue;
    }

    /**
     * Adiciona uma nova viagem ao cliente
     * @param t Viagem a adicinar
     */
    public void addTrip(Trip t){
        super.addTrip(t);
        this.position.setLocation(t.getEnd());
        this.points += t.getPrice() / 2 + t.distance() / 4;
    }
}