import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.*;


/**
 * Bike for UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bike extends Vehicle {



    /** CONSTRUTORES */

    /**
     * Cria um Mota
     */
    public Bike() {
        this.setRegistration(null);
        this.setSpeed(65);
        this.setPrice(.90);
        this.setReliable(0);
        this.setAvailable(true);
        this.setSeats(1);
        this.setPosition(new Point2D.Double(0, 0));
        this.setQueue(null);
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
    public Bike(LinkedList<Client> queue, String registration, double reliable, boolean available, Point2D.Double position) {
        this.setRegistration(registration);
        this.setReliable(reliable);
        this.setAvailable(available);
        this.setPosition(new Point2D.Double(position.getX(), position.getY()));
        this.setSeats(1);
        this.setSpeed(65);
        this.setPrice(.90);
        this.setQueue(queue);
    }

    /**
     * Constroi um Mota a partir de um já existente
     *
     * @param v
     */
    public Bike(Bike v) {
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
     * Faz a cópia de uma mota
     *
     * @return Cópia de Mota
     */
    public Bike clone(){
    	return new Bike(this);
    }

	/**
     * Calcula o trânsito à volta de uma mota
	 *
	 * @param vehicles Map de todos os veículos
     * @return Nível de trânsito
     */
	public int calculateTraffic(HashMap<String,Vehicle> vehicles){
		double minDist = 1;
		int traffic = 1;
		for (Vehicle v : vehicles.values())
			if (v.getPosition().distance(this.getPosition()) <= minDist);
				traffic++;

		return traffic;
	}

}
