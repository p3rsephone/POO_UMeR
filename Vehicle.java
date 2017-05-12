import java.awt.geom.Point2D;
import java.util.*;
/**
 * Vehicle class for UMeR.
 */

public abstract class Vehicle {

    /** Variáveis de Instância */

    private String licencePlate;
    private double speed;
    private double price;
    private double reliable;
    private boolean available;
    private int seats;
    private Point2D.Double position;
    private LinkedList<Client> queue = new LinkedList<>();
    private ArrayList<Trip> trips = new ArrayList<>();
    private String owner = null;



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
     * @return speed Velocidade média do Veiculo
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
    public LinkedList<Client> getQueue() {
        LinkedList<Client> queue = new LinkedList<>();
        if (this.queue != null)
            for(Client c : this.queue) {
                queue.add(c.clone());
            }
        return queue;
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
     * Altera a velocidade de um veiculo
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
     * Altera a fila de espera de um veiculo
     * @param queue Nova fila de espera
     */
    public void setQueue(LinkedList<Client> queue){
        this.queue = new LinkedList<>(queue);
    }

    /**
     * Adiciona um Cliente a uma fila de espera
     * @param c Cliente a adicionar
     */
    public void addClient(Client c){
        this.queue.addLast(c.clone());
    }

    /**
     * Compara dois veiculos
     *
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
            for (Client c : this.queue) {
                s.append(i + "º lugar :" + c.getEmail() + "\n");
                i++;
            }
            return s.toString();
        }
        else return "Fila de espera vazia.";
    }

    /**
     * Associa um condutor/empresa a um veículo
     * @param owner Condutor/Empresa
     */
    public void addOwner(String owner){
        this.owner = owner;
    }

    /**
     * Adiciona uma viagem a um veículo
     * @param t Viagem a adicionar
     */
    public void addTrip(Trip t){
        this.trips.add(t);
    }
}
