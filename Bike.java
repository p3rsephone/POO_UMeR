import java.awt.geom.Point2D;
import java.util.LinkedList;



/**
 * Write a description of class Bike here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bike extends Vehicle {

    private LinkedList<Client> queue;


    /** CONSTRUTORES */

    /**
     * Cria um Mota
     */
    public Bike() {
        super();
        this.queue = new LinkedList<>();
    }


    /**
     * Constroi um Mota passado os parâmetros
     *
     * @param queue
     * @param registration
     * @param speed
     * @param price
     * @param reliable
     * @param available
     * @param seats
     * @param position
     */
    public Bike(LinkedList<Client> queue, String registration, int speed, double price, int reliable, int available, int seats, Point2D.Double position) {
        super(registration, speed, price, reliable, available, seats, position);
        this.setSeats(1);
        this.setSpeed(65);
        this.setPrice(.90);
        if (queue != null) this.queue = new LinkedList<>(queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Constroi um Mota a partir de um já existente
     *
     * @param v
     */
    public Bike(Bike v) {
        super(v.getRegistration(), v.getSpeed(), v.getPrice(), v.getReliable(), v.getAvailable(), v.getSeats(), v.getPosition());
        if (v.queue != null) this.queue = new LinkedList<>(v.queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Faz a cópia de uma mota
     *
     * @return Cópia de Mota
     */
    public Bike clone(){
    return new Bike(this);
    }


}
