import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.*;



/**
 * Helicopter for UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Helicopter extends Vehicle {


    /** CONSTRUTORES */

    /**
     * Cria um Helicoptero
     */
    public Helicopter() {
        this.setRegistration(null);
        this.setSpeed(110);
        this.setPrice(2.85);
        this.setReliable(0);
        this.setAvailable(true);
        this.setSeats(3);
        this.setPosition(new Point2D.Double(0, 0));
        this.setQueue(null);
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
    public Helicopter(LinkedList<Client> queue, String registration, double reliable, boolean available, Point2D.Double position) {
        this.setRegistration(registration);
        this.setReliable(reliable);
        this.setAvailable(available);
        this.setPosition(new Point2D.Double(position.getX(), position.getY()));
        this.setSeats(3);
        this.setSpeed(110);
        this.setPrice(2.85);
        this.setQueue(queue);
    }

    /**
     * Constroi um Helicoptero a partir de um já existente
     *
     * @param v
     */
    public Helicopter(Helicopter v) {
        this.setRegistration(v.getRegistration());
        this.setSpeed(v.getSpeed());
        this.setPrice(v.getPrice());
        this.setReliable(v.getReliable());
        this.setAvailable(v.isAvailable());
        this.setSeats(v.getSeats());
        this.setPosition(v.getPosition());
        this.setQueue(v.getQueue());
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
	public int calculateTraffic(HashMap<String,Vehicle> vehicles){
		return 1;
	}
}
