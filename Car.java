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
    
    
    


    /** CONSTRUTORES */

    /**
     * Cria um Carro
     */
    public Car() {
        this.setRegistration(null);
        this.setSpeed(80);
        this.setPrice(1.10);
        this.setReliable(0);
        this.setAvailable(true);
        this.setSeats(4);
        this.setPosition(new Point2D.Double(0, 0));
        this.setQueue(null);
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
    public Car(LinkedList<Client> queue, String registration, double reliable, boolean available, Point2D.Double position) {
        this.setRegistration(registration);
        this.setReliable(reliable);
        this.setAvailable(available);
        this.setPosition(new Point2D.Double(position.getX(), position.getY()));
        this.setSeats(4);
        this.setSpeed(80);
        this.setPrice(1.10);
        this.setQueue(queue);
    }

    /**
     * Constroi um Carro a partir de um já existente
     *
     * @param v
     */
    public Car(Car v) {
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
     * @param vehicles Map de todos os veículos
     * @return Nível de trânsito
     */
    public int calculateTraffic(HashMap<String,Vehicle> vehicles){
        double minDist = 2;
        int traffic = 1;
        for (Vehicle v : vehicles.values())
            if (v.getPosition().distance(this.getPosition()) <= minDist);
                traffic++;

        return traffic;
    }


}
