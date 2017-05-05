import java.awt.geom.Point2D;
import java.util.LinkedList;



/**
 * Helicopter for UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Helicopter extends Vehicle {

    private LinkedList<Client> queue;


    /** CONSTRUTORES */

    /**
     * Cria um Helicoptero
     */
    public Helicopter() {
        super();
        this.queue = new LinkedList<>();
    }


    /**
     * Constroi um Helicoptero passado os parâmetros
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
    public Helicopter(LinkedList<Client> queue, String registration, double speed, double price, double reliable, boolean available, int seats, Point2D.Double position) {
        super(registration, speed, price, reliable, available, seats, position);
        this.setSeats(3);
        this.setSpeed(0.6);
        this.setPrice(2.75);
        if (queue != null) this.queue = new LinkedList<>(queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Constroi um Helicoptero a partir de um já existente
     *
     * @param v
     */
    public Helicopter(Helicopter v) {
        super(v.getRegistration(), v.getSpeed(), v.getPrice(), v.getReliable(), v.getAvailable(), v.getSeats(), v.getPosition());
        if (v.queue != null) this.queue = new LinkedList<>(v.queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Faz a cópia de um Helicoptero
     *
     * @return Cópia de Helicoptero
     */
    public Helicopter clone(){
    	return new Helicopter(this);
    }

	/**
	 * Calcula o trânsito à volta de um Helicoptero
	 *
	 * @return Nível de trânsito
	 */
	public int calculateTraffic(){
		return 1;
	}
}
