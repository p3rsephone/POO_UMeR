import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.*;

/**
 * Bike for UMeR.
 */

public class Bike extends Vehicle {

    /** CONSTRUTORES */

    /**
     * Cria uma Mota sem parâmetros
     */
    public Bike() {
        this.setRegistration(null);
        this.setSpeed(65);
        this.setPrice(.90);
        this.setReliable(0);
        this.setAvailable(true);
        this.setSeats(1);
        this.setPosition(new Point2D.Double(0, 0));
    }

    /**
     * Constroi uma Mota passado os parâmetros
     * @param registration Matrícula
     * @param reliable     Fator de fiabilidade
     * @param position     Posição atual
     */
    public Bike(String registration, double reliable, Point2D.Double position) {
        this.setRegistration(registration);
        this.setReliable(reliable);
        this.setAvailable(true);
        this.setPosition(new Point2D.Double(position.getX(), position.getY()));
        this.setSeats(1);
        this.setSpeed(65);
        this.setPrice(1.1);
    }

    /**
     * Constroi uma Mota a partir de um já existente
     * @param v Mota já definida
     */
    public Bike(Bike v) {
        this.setRegistration(v.getRegistration());
        this.setSpeed(v.getSpeed());
        this.setPrice(v.getPrice());
        this.setReliable(v.getReliable());
        this.setAvailable(v.isAvailable());
        this.setSeats(v.getSeats());
        this.setPosition(v.getPosition());
    }

    /**
     * Faz a cópia de uma mota
     * @return Cópia de Mota
     */
    public Bike clone(){
    	return new Bike(this);
    }

}
