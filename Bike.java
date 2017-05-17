import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.*;

/**
 * Bike for UMeR.
 */

public class Bike extends Vehicle implements Serializable {

    /** CONSTRUTORES */

    /**
     * Cria uma Mota sem parâmetros
     */
    public Bike() {
        super();
        this.setSpeed(75);
        this.setPrice(0.9);
        this.setSeats(1);
    }

    /**
     * Constroi uma Mota passado os parâmetros
     * @param licencePlate Matrícula
     * @param reliable     Fator de fiabilidade
     * @param position     Posição atual
     */
    public Bike(String licencePlate, double reliable, Point2D.Double position, String owner) {
        super(licencePlate, reliable, position, true, null, null, null, owner);
        this.setSpeed(75);
        this.setPrice(0.9);
        this.setSeats(1);
    }

    /**
     * Constroi uma Mota a partir de uma já existente
     * @param v Mota
     */
    public Bike(Bike v) {
        super(v.getLicencePlate(), v.getReliable(), v.getPosition(), v.isAvailable(), v.getQueue(), v.getQueueInfo(), v.getTrips(), v.getOwner());
        this.setSpeed(75);
        this.setPrice(0.9);
        this.setSeats(1);
    }

    /**
     * Faz a cópia de uma mota
     * @return Cópia de Mota
     */
    public Bike clone(){
    	return new Bike(this);
    }

}
