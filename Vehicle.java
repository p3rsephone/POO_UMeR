import java.awt.geom.Point2D;
import java.io.Serializable;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;



/**
 * Vehicle class for UMeR.
 */

public abstract class Vehicle implements Serializable {

    /** Variáveis de Instância */

    private String licencePlate; ///< Matricula
    private double speed; ///< Velocidade Media
    private double price; ///< Preço Médio por km
    private double reliable; ///< Grau de fiabilidade
    private boolean available; ///< Disponibilidade
    private int seats; ///< Numero de lugares
    private Point2D.Double position; ///< Posição
    private LinkedList<String> queue = new LinkedList<>(); ///< Fila de espera
    private HashMap<String, ArrayList<Point2D.Double>> queueInfo; ///< Informação da fila de espera
    private ArrayList<Trip> trips; ///< Viagens de um veiculo
    private String owner = null; ///< Dono do veiculo



    /**
     * Construtor a partir de parametros definidos
     * @param licencePlate  Matricula
     * @param reliable      Grau de fiabilidade
     * @param position      Posição
     * @param available     Se está disponível ou não
     * @param queue         Fila de espera
     * @param queueInfo     Informação da fila de espera
     * @param trips         Viagens
     * @param owner         Dono do veículo
     */
    public Vehicle(String licencePlate, double reliable, Point2D.Double position, boolean available, LinkedList<String> queue, HashMap<String, ArrayList<Point2D.Double>> queueInfo, ArrayList<Trip> trips, String owner){
        this.licencePlate = licencePlate;
        this.reliable = reliable;
        this.position = new Point2D.Double(position.getX(), position.getY());
        this.available = true;
        this.queue = new LinkedList<>();
        setQueue(queue);
        this.queueInfo = new HashMap<>();
        setQueueInfo(queueInfo);
        this.trips = new ArrayList<>();
        setTrips(trips);
        this.owner = owner;
    }



    /**
     * Contrutor vazio
     */
    public Vehicle(){
        this.licencePlate = null;
        this.reliable = 0;
        this.position = new Point2D.Double(0,0);
        this.queue = new LinkedList<>(null);
        this.queueInfo = new HashMap<>(null);
        this.trips = new ArrayList<>(null);
        this.owner = null;
    }



    /**Métodos de Instância*/

    /**
     * Retorna a matricula de um veiculo
     * @return licencePlate Matricula do Veículo
     */
    public String getLicencePlate() {
        return this.licencePlate;
    }



    /**
     * Retorna a velocidade média por km de um veiculo
     * @return speed Velocidade Média do Veiculo
     */
    public double getSpeed() {
        return this.speed;
    }



    /**
     * Retorna o preço médio por km de um veiculo
     * @return price Preço do Veiculo
     */
    public double getPrice() {
        return this.price;
    }


    /**
     * Retorna o fator de fiabilidade de um veiculo
     * @return reliable Fator de fiabilidade do veiculo
     */
    public double getReliable() {
        return this.reliable;
    }



    /**
     * Retorna a disponibilidade de um veiculo
     * @return available Disponibilidade do veiculo
     */
    public boolean isAvailable() {
        return this.available;
    }



    /**
     * Retorna o numero de lugares de um veiculo
     * @return seats Numeros de lugares do veiculo
     */
    public int getSeats() {
        return this.seats;
    }



    /**
     * Retorna a posição de um veiculo
     * @return position Posiçao do veiculo
     */
    public Point2D.Double getPosition() {
        return new Point2D.Double(this.position.getX(), this.position.getY());
    }



    /**
     * Retorna uma cópia da fila de espera
     * @return Fila de Espera
     */
    public LinkedList<String> getQueue() {
        LinkedList<String> queue = new LinkedList<>();
        if (this.queue != null)
            for(String c : this.queue) {
                queue.add(c);
            }
        return queue;
    }



    /**
     * Retorna uma cópia da informação da fila de espera
     * @return Fila de Espera
     */
    public HashMap<String, ArrayList<Point2D.Double>> getQueueInfo() {
        HashMap<String, ArrayList<Point2D.Double>> queue = new HashMap<>();
        if (this.queue != null)
            for(String c : this.queueInfo.keySet()) {
                queue.put(c, this.queueInfo.get(c));
            }
        return queue;
    }



    /**
     * Retorna uma cópia da informação da lista de viagens
     * @return Lista de viagens
     */
    public ArrayList<Trip> getTrips(){
        ArrayList<Trip> trips = new ArrayList<>();
        for (Trip t: this.trips)
            trips.add(t);
        return trips;
    }



    /**
     * Indica o dono do veículo
     * @return Dono
     */
    public String getOwner(){
        return this.owner;
    }



    /**
     * Altera a matricula de um veiculo
     * @param licencePlate Nova matricula
     */
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }



    /**
     * Altera a velocidade média de um veiculo
     * @param speed Nova velocidade
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }



    /**
     * Altera o preço médio por km de um veiculo
     * @param price Novo preço
     */
    public void setPrice(double price) {
        this.price = price;
    }



    /**
     * Altera o fator de fiabilidade de um veiculo
     * @param reliable Novo fator de fiabilidade
     */
    public void setReliable(double reliable) {
        this.reliable = reliable;
    }



    /**
     * Altera a disponibilidade de um veiculo
     * @param available Nova disponibilidade
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }



    /**
     * Altera o numero de lugares de um veiculo
     * @param seats Novo numero de lugares
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }



    /**
     * Altera a posição de um veiculo
     * @param position Nova posição
     */
    public void setPosition(Point2D.Double position) {
        this.position = new Point2D.Double(position.getX(), position.getY());

    }


    /**
     * Altera a fila de espera
     * @param queue Nova fila de espera
     */
    public void setQueue(LinkedList<String> queue){
        if (queue != null)
            for (String s: queue)
                this.queue.addLast(s);
    }


    /**
     * Altera a informação da fila de espera
     * @param queueInfo Nova informação
     */
    public void setQueueInfo(HashMap<String, ArrayList<Point2D.Double>> queueInfo){
        if (queueInfo != null) {
            ArrayList<Point2D.Double> a = new ArrayList<>();
            for (String s : queueInfo.keySet()) {
                for (Point2D.Double p : queueInfo.get(s))
                    a.add(new Point2D.Double(p.getX(), p.getY()));
                this.queueInfo.put(s, a);
            }
        }
    }


    /**
     * Altera o arraylist de viagens
     * @param trips Novas viagens
     */
    public void setTrips(ArrayList<Trip> trips){
        ArrayList<Trip> list = new ArrayList<>();
        if (trips != null){
                for (Trip t: trips)
                    list.add(t.clone());
                }
        this.trips = list;
    }

    /**
     * Associa um condutor/empresa a um veículo
     * @param owner Condutor/Empresa
     */
    public void setOwner(String owner){
        this.owner = owner;
    }


    /**
     * Compara dois veiculos
     * @param v Veiculo
     * @return É o mesmo veiculo (true) ou não (false)
     */
    public boolean equals(Vehicle v) {
        return this.licencePlate.equals(v.getLicencePlate());
    }


    /**
     * Faz a cópia de um veiculo
     * @return Cópia de Veiculo
     */
    public abstract Vehicle clone();

    /**
     * Imprime a informação de um veículo
     * @return String com a informação
     */
    public String toString(){
        return  "Tipo : " + this.getClass() + "\n" +
                "Matricula : " + this.licencePlate + "\n" +
                "Velocidade média por km : " + this.speed + "\n" +
                "Preço médio por km : " + this.price    + "\n" +
                "Fator de fiabilidade : " + this.reliable + "\n" +
                "Disponibilidade : " + this.available   + "\n" +
                "Numero de lugares: " + this.seats + "\n" +
                "Posição : " + "(" + this.position.getX() + "," + this.position.getY() + ")\n"+
                "Owner : " + this.owner + "\n" +
                "---------\nFila de Espera : \n"+ printQueue() +"\n\n";
    }


    /**
     * Imprime a lista de espera de um veículo
     * @return String com a lista de espera
     */
    public String printQueue(){
        if (this.queue.size() > 0) {
            StringBuilder s = new StringBuilder();
            int i = 1;
            for (String c : this.queue) {
                s.append(i + "º lugar :" + c + "\n");
                i++;
            }
            return s.toString();
        }
        else return "Fila de espera vazia.";
    }


    /**
     * Adiciona um Cliente a uma fila de espera
     * @param c Cliente a adicionar
     */
    public void addClient(String c, Point2D.Double start, Point2D.Double end){
        ArrayList<Point2D.Double> p = new ArrayList<>();
        p.add(new Point2D.Double(start.getX(), start.getY()));
        p.add(new Point2D.Double(end.getX(), end.getY()));
        this.queue.addLast(c);
        this.queueInfo.put(c, p);
    }



    /**
     * Remove um cliente da fila de espera
     * @param c Cliente a remover
     */
    public void removeClient(String c){
        this.queue.remove(c);
        this.queueInfo.remove(c);
    }


    /**
     * Adiciona uma viagem a um veículo
     * @param t Viagem a adicionar
     */
    public void addTrip(Trip t){
        this.trips.add(t);
        this.position.setLocation(t.getEnd().getX(), t.getEnd().getY());
    }


    /**
     * Retorna a quantia de dinheiro gerada entre duas datas
     * @param t1  Data 1
     * @param t2  Data 2
     * @return Dinheiro gerado
     */
    public int moneyGeneratedBetween(LocalDate t1, LocalDate t2){
        double money = 0;
        for (Trip t: trips)
            if ((t.getDate().isAfter(t1) || t.getDate().isEqual(t1))
                && (t.getDate().isBefore(t2)) || t.getDate().isEqual(t2))
                money += t.getPrice();
        return (int) money;
    }
}

