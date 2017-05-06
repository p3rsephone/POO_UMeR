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



    /** CONSTRUTORES */

    /**
     * Cria uma Carrinha
     */
    public Van() {
        this.setRegistration(null);
        this.setSpeed(65);
        this.setPrice(1.80);
        this.setReliable(0);
        this.setAvailable(true);
        this.setSeats(9);
        this.setPosition(new Point2D.Double(0, 0));
        this.setQueue(null);
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
    public Van(LinkedList<Client> queue, String registration, double reliable, boolean available, Point2D.Double position) {
        this.setRegistration(registration);
        this.setReliable(reliable);
        this.setAvailable(available);
        this.setPosition(new Point2D.Double(position.getX(), position.getY()));
        this.setSeats(9);
        this.setSpeed(65);
        this.setPrice(1.80);
        this.setQueue(queue);
    }

    /**
     * Constroi uma Carrinha a partir de uma já existente
     *
     * @param v
     */
    public Van(Van v) {
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
	 * @param vehicles Map de todos os veículos
	 * @return Nível de trânsito
	 */
	public int calculateTraffic(HashMap<String,Vehicle> vehicles){
		double minDist = 3;
		int traffic = 1;;
		for (Vehicle v : vehicles.values())
			if (v.getPosition().distance(this.getPosition()) <= minDist);
				traffic++;

		return traffic;
	}
}
