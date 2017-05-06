import java.awt.geom.Point2D;
import java.util.*;
/**
 * Vehicle class for UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Vehicle {

    private String registration;
    private double speed;
    private double price;
    private double reliable;
    private boolean available;
    private int seats;
    private Point2D.Double position;
    private LinkedList<Client> queue;
    
    

    /**
     * Retorna a matricula de um veiculo
     *
     * @return registration Matricula de Veiculo
     */
    public String getRegistration() {
        return this.registration;
    }

    /**
     * Retorna a velocidade média por km de um veiculo
     *
     * @return speed Velocidade média do Veiculo
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Retorna o preço médio por km de um veiculo
     *
     * @return price Preço do Veiculo
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Retorna o fator de fiabilidade de um veiculo
     *
     * @return reliable Fator de fiabilidade do veiculo
     */
    public double getReliable() {
        return this.reliable;
    }

    /**
     * Retorna a disponibilidade de um veiculo
     *
     * @return available Disponibilidade do veiculo
     */
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * Retorna o numero de lugares de um veiculo
     *
     * @return seats Numeros de lugares do veiculo
     */
    public int getSeats() {
        return this.seats;
    }

    /**
     * Retorna a posição de um veiculo
     *
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
    
    
    /**SETTERS*/

    /**
     * Altera a matricula de um veiculo
     *
     * @param registration Nova matricula
     */
    public void setRegistration(String registration) {
        this.registration = registration;
    }

    /**
     * Altera a velocidade de um veiculo
     *
     * @param speed Nova velocidade
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Altera o preço medio por km de um veiculo
     *
     * @param price Nova preço
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Altera o fator de fiabilidade de um veiculo
     *
     * @param reliable Novo fator de fiabilidade
     */
    public void setReliable(double reliable) {
        this.reliable = reliable;
    }

    /**
     * Altera a disponibilidade de um veiculo
     *
     * @param available Nova disponibilidade
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Altera o numero de lugares de um veiculo
     *
     * @param seats Novo numero de lugares
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Altera a posição de um veiculo
     *
     * @param position Nova posição
     */
    public void setPosition(Point2D.Double position) {
        this.position = new Point2D.Double(position.getX(), position.getY());
    }

    /**
     * Altera a fila de espera de um veiculo
     * 
     * @param queue Nova fila de espera
     */
    public void setQueue(LinkedList<Client> queue){
        this.queue = queue;
       }
       
    /**MÉTODOS DE INSTÂNCIA*/

    /**
     * Adiciona um Cliente a uma fila de espera
     * @param c Cliente a adicionar
     */
    public void addClient(Client c){
        this.queue.addFirst(c.clone());
    }
    
    /**
     * Compara dois veiculos
     *
     * @param v Veiculo
     * @return É o mesmo veiculo (true) ou não (false)
     */
    public boolean equals(Vehicle v) {
        return this.registration.equals(v.getRegistration());
    }


    /**
     * Faz a cópia de um veiculo
     *
     * @return Cópia de Veiculo
     */
    public abstract Vehicle clone();

    /**
     * Calcula o trânsito à volta de um veículo
     *
     * @return Nível de trânsito
     */
    public abstract int calculateTraffic(HashMap<String,Vehicle> vehicles); //Só vai ajudar a calcular o tempo real


    /**
     * toString de um veiculo
     * @return String
     */
    public String toString(){
        return  "Tipo : " + this.getClass() + "\n" +
                "Matricula : "      + this.registration         + "\n" +
                "Velocidade média por km : "                + this.speed        + "\n" +
                "Preço médio por km : "             + this.price    + "\n" +
                "Fator de fiabilidade : "           + this.reliable         + "\n" +
                "Disponibilidade : " + this.available   + "\n" +
                "Numero de lugares: " + this.seats + "\n" +
                "Posição : "            + "(X - " + this.position.getX() + ", Y - " + this.position.getY() + ")\n"+
                "Fila de Espera : "+ (this.queue)+"\n";
    }
}
