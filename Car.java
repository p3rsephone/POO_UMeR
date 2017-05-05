import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.*;


/**
 * Car for UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Car extends Vehicle {

    private LinkedList<Client> queue;


    /** CONSTRUTORES */

    /**
     * Cria um Carro
     */
    public Car() {
        super( );
        this.queue = new LinkedList<>();
    }


    /**
     * Constroi um Carro passado os parâmetros
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
    public Car(LinkedList<Client> queue, String registration, double speed, double price, double reliable, boolean available, int seats, Point2D.Double position) {
        super(registration, speed, price, reliable, available, seats, position);
        this.setSeats(4);
        this.setSpeed(0.7);
        this.setPrice(1.10);
        if (queue != null) this.queue = new LinkedList<>(queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Constroi um Carro a partir de um já existente
     *
     * @param v
     */
    public Car(Car v) {
        super(v.getRegistration(), v.getSpeed(), v.getPrice(), v.getReliable(), v.getAvailable(), v.getSeats(), v.getPosition());
        if (v.queue != null) this.queue = new LinkedList<>(v.queue);
        else this.queue = new LinkedList<>();
    }

    /**
     * Faz a cópia de um carro
     *
     * @return Cópia de Carro
     */
    public Car clone(){
    	return new Car(this);
    }

	/**
     * Calcula o trânsito à volta de um carro
     *
     * @return Nível de trânsito
     */
	public int calculateTraffic(){
		double minDist = 2;
		int traffic = 1;
		HashMap<String,Vehicle> vehicles = new HashMap<>();
		for (Vehicle v : vehicles.values())
			if (v.getPosition().distance(this.getPosition()) <= minDist);
				traffic++;

		return traffic;
	}


}
