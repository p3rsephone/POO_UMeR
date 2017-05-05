import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.*;


/**
 * Van for UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Van extends Vehicle {

    private LinkedList<Client> queue;


    /** CONSTRUTORES */

    /**
     * Cria uma Carrinha
     */
    public Van() {
        super();
        this.queue = new LinkedList<>();
    }


    /**
     * Constroi uma Carrinha passado os parâmetros
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
    public Van(LinkedList<Client> queue, String registration, double speed, double price, double reliable, boolean available, int seats, Point2D.Double position) {
        super(registration, speed, price, reliable, available, seats, position);
        this.setSeats(9);
        this.setSpeed(0.9);
        this.setPrice(1.80);
        if (queue != null) this.queue = new LinkedList<>(queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Constroi uma Carrinha a partir de uma já existente
     *
     * @param v
     */
    public Van(Van v) {
        super(v.getRegistration(), v.getSpeed(), v.getPrice(), v.getReliable(), v.getAvailable(), v.getSeats(), v.getPosition());
        if (v.queue != null) this.queue = new LinkedList<>(v.queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Faz a cópia de uma carrinha
     *
     * @return Cópia de Carrinha
     */
    public Van clone(){
    	return new Van(this);
    }

	/**
	 * Calcula o trânsito à volta de uma carrinha
	 *
	 * @return Nível de trânsito
	 */
	public int calculateTraffic(){
		double minDist = 3;
		int traffic = 1;
		HashMap<String,Vehicle> vehicles = new HashMap<>();
		for (Vehicle v : vehicles.values())
			if (v.getPosition().distance(this.getPosition()) <= minDist);
				traffic++;

		return traffic;
	}
}
