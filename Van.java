import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.*;

/**
 * Van for UMeR.
 */

public class Van extends Vehicle {

    /** CONSTRUTORES */

    /**
     * Cria uma Carrinha sem parâmetros
     */
    public Van() {
        this.setLicencePlate(null);
        this.setSpeed(65);
        this.setPrice(1.90);
        this.setReliable(0);
        this.setAvailable(true);
        this.setSeats(9);
        this.setPosition(new Point2D.Double(0, 0));
    }

    /**
     * Cria um Carrinha passado os parâmetros
     * @param licencePlate Matrícula
     * @param reliable     Fator de fiabilidade
     * @param position     Posição atual
     */
    public Van(String licencePlate, double reliable, Point2D.Double position) {
        this.setLicencePlate(licencePlate);
        this.setReliable(reliable);
        this.setAvailable(true);
        this.setPosition(new Point2D.Double(position.getX(), position.getY()));
        this.setSeats(9);
        this.setSpeed(65);
        this.setPrice(1.80);
    }

    /**
     * Constroi uma Carrinha a partir de uma já existente
     * @param v Carrinha já definida
     */
    public Van(Van v) {
        this.setLicencePlate(v.getLicencePlate());
        this.setSpeed(v.getSpeed());
        this.setPrice(v.getPrice());
        this.setReliable(v.getReliable());
        this.setAvailable(v.isAvailable());
        this.setSeats(v.getSeats());
        this.setPosition(v.getPosition());
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
