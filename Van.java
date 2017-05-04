import java.awt.geom.Point2D;
import java.util.LinkedList;



/**
 * Write a description of class Van here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Van extends Vehicle {

    private LinkedList<Client> queue;


    /** CONSTRUTORES */

    /**
     * Cria um Carrinha
     */
    public Van() {
        super();
        this.queue = new LinkedList<>();
    }


    /**
     * Constroi um Carrinha passado os parâmetros
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
    public Van(LinkedList<Client> queue, String registration, int speed, double price, int reliable, int available, int seats, Point2D.Double position) {
        super(registration, speed, price, reliable, available, seats, position);
        this.setSeats(9);
        this.setSpeed(65);
        this.setPrice(1.80);
        if (queue != null) this.queue = new LinkedList<>(queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Constroi um Carrinha a partir de um já existente
     *
     * @param v
     */
    public Van(Van v) {
        super(v.getRegistration(), v.getSpeed(), v.getPrice(), v.getReliable(), v.getAvailable(), v.getSeats(), v.getPosition());
        if (v.queue != null) this.queue = new LinkedList<>(v.queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Faz a cópia de um carrinha
     *
     * @return Cópia de Carrinha
     */
    public Van clone(){
    return new Van(this);
    }
}
