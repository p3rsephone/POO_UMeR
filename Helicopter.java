import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.*;

/**
 * Helicopter for UMeR.
 */

public class Helicopter extends Vehicle implements Serializable {

    /** CONSTRUTORES */

    /**
     * Cria um Helicoptero sem parâmetros
     */
    public Helicopter() {
        super();
        this.setSpeed(110);
        this.setPrice(8.5);
        this.setSeats(3);
    }

    /**
     * Constroi um Helicoptero passado os parâmetros
     * @param licencePlate Matrícula
     * @param reliable     Fator de fiabilidade
     * @param position     Posição atual
     */
    public Helicopter(String licencePlate, double reliable, Point2D.Double position, String owner) {
        super(licencePlate, reliable, position, true, null, null, null, owner);
        this.setPrice(8.5);
        this.setSpeed(110);
        this.setSeats(3);
    }

    /**
     * Constroi um Helicoptero a partir de um já existente
     * @param v Helicóptero
     */
    public Helicopter(Helicopter v) {
        super(v.getLicencePlate(), v.getReliable(), v.getPosition(), v.isAvailable(), v.getQueue(), v.getQueueInfo(), v.getTrips(), v.getOwner());
        this.setSpeed(110);
        this.setPrice(8.5);
        this.setSeats(3);
    }

    /**
     * Faz a cópia de um Helicoptero
     * @return Cópia de Helicoptero
     */
    public Helicopter clone(){
    	return new Helicopter(this);
    }
}
