import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.*;

/**
 * Van for UMeR.
 */

public class Van extends Vehicle implements Serializable {

    /** CONSTRUTORES */

    /**
     * Cria uma Carrinha sem parâmetros
     */
    public Van() {
        super();
        this.setSpeed(55);
        this.setPrice(1.90);
        this.setSeats(9);
    }

    /**
     * Constroi uma Carrinha passado os parâmetros
     * @param licencePlate Matrícula
     * @param reliable     Fator de fiabilidade
     * @param position     Posição atual
     */
    public Van(String licencePlate, double reliable, Point2D.Double position, String owner) {
        super(licencePlate, reliable, position, true, null, null, null, owner);
        this.setSpeed(55);
        this.setPrice(1.90);
        this.setSeats(9);
    }

    /**
     * Constroi uma Carrinha a partir de uma já existente
     * @param v Carrinha
     */
    public Van(Van v) {
        super(v.getLicencePlate(), v.getReliable(), v.getPosition(), v.isAvailable(), v.getQueue(), v.getQueueInfo(), v.getTrips(), v.getOwner());
        this.setSpeed(55);
        this.setPrice(1.90);
        this.setSeats(9);
    }

    /** Métodos de Instância */

    /**
     * Faz a cópia de uma carrinha
     * @return Cópia de Carrinha
     */
    public Van clone(){
        return new Van(this);
    }
}
